package com.sunfusheng.spi.plugin

import org.apache.commons.io.IOUtils
import org.objectweb.asm.*

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry
/**
 * @author by sunfusheng on 2019/3/19
 */
class RegisterCodeGenerator {
    static final String GENERATE_TO_CLASS_NAME = 'com/sunfusheng/spi/api/ServiceProvider.class'
    static final String GENERATE_TO_METHOD_NAME = 'register'

    static File mServiceProviderFile
    static List<String> mProvidersList = new ArrayList<>()

    RegisterCodeGenerator() {
    }

    void insertRegisterCode() {
        if (mProvidersList != null && !mProvidersList.isEmpty() &&
                mServiceProviderFile != null && mServiceProviderFile.name.endsWith(".jar")) {
            insertRegisterCodeIntoJarFile(mServiceProviderFile)
        }
    }

    private File insertRegisterCodeIntoJarFile(File jarFile) {
        if (jarFile) {
            def optJar = new File(jarFile.getParent(), jarFile.name + ".opt")
            if (optJar.exists()) {
                optJar.delete()
            }

            def file = new JarFile(jarFile)
            Enumeration enumeration = file.entries()
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(optJar))

            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement()
                String entryName = jarEntry.getName()
                println '【spi-plugin】 insertInitCodeIntoJarFile() entryName: ' + entryName
                ZipEntry zipEntry = new ZipEntry(entryName)
                InputStream inputStream = file.getInputStream(jarEntry)
                jarOutputStream.putNextEntry(zipEntry)
                if (GENERATE_TO_CLASS_NAME == entryName) {
                    def bytes = visitServiceProviderAndInsertCode(inputStream)
                    jarOutputStream.write(bytes)
                } else {
                    jarOutputStream.write(IOUtils.toByteArray(inputStream))
                }
                inputStream.close()
                jarOutputStream.closeEntry()
            }
            jarOutputStream.close()
            file.close()

            if (jarFile.exists()) {
                jarFile.delete()
            }
            optJar.renameTo(jarFile)
            println '【spi-plugin】 jarFile.absolutePath: ' + jarFile.absolutePath
        }
        return jarFile
    }

    private byte[] visitServiceProviderAndInsertCode(InputStream inputStream) {
        ClassReader cr = new ClassReader(inputStream)
        ClassWriter cw = new ClassWriter(cr, 0)
        ClassVisitor cv = new ServiceProviderClassVisitor(Opcodes.ASM6, cw)
        cr.accept(cv, ClassReader.EXPAND_FRAMES)
        return cw.toByteArray()
    }

    class ServiceProviderClassVisitor extends ClassVisitor {
        ServiceProviderClassVisitor(int api, ClassVisitor cv) {
            super(api, cv)
            println '【spi-plugin】 ServiceProviderClassVisitor'
        }

        void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces)
        }

        @Override
        MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions)
            println '【spi-plugin】 visitMethod() name: ' + name
            if (name == GENERATE_TO_METHOD_NAME) {
                mv = new RegisterMethodVisitor(Opcodes.ASM6, mv)
            }
            return mv
        }
    }

    class RegisterMethodVisitor extends MethodVisitor {
        RegisterMethodVisitor(int api, MethodVisitor mv) {
            super(api, mv)
            println '【spi-plugin】 RegisterMethodVisitor'
        }

        @Override
        void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)) {
                mProvidersList.each { provider ->
                    provider = provider.replaceAll("/", ".")
                    println '【spi-plugin】 visitInsn() provider: ' + provider
                    mv.visitFieldInsn(Opcodes.GETSTATIC, "com/sunfusheng/spi/api/ProvidersPool",
                            "registry",
                            "Lcom/sunfusheng/spi/api/ProvidersRegistry;")
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, provider,
                            "register",
                            "(Lcom/sunfusheng/spi/api/ProvidersRegistry;)V",
                            false)
                }
                super.visitInsn(opcode)
            }
        }

        @Override
        void visitMaxs(int maxStack, int maxLocals) {
            super.visitMaxs(maxStack + 1, maxLocals)
        }
    }
}
