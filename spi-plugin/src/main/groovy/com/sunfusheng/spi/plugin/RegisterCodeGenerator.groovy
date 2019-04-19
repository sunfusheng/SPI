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

    static File mServiceProviderFile = null
    static Map<String, Set<String>> mProvidersMap = new HashMap<>()

    static void insertRegisterCode() {
        if (mServiceProviderFile != null && mServiceProviderFile.name.endsWith(".jar") && mProvidersMap != null && mProvidersMap.size() > 0) {
            insertRegisterCodeIntoJarFile(mServiceProviderFile)
            mServiceProviderFile = null
            mProvidersMap.clear()
        }
    }

    private static void insertRegisterCodeIntoJarFile(File jarFile) {
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
        }
    }

    private static byte[] visitServiceProviderAndInsertCode(InputStream inputStream) {
        ClassReader cr = new ClassReader(inputStream)
        ClassWriter cw = new ClassWriter(cr, 0)
        ClassVisitor cv = new ServiceProviderClassVisitor(Opcodes.ASM6, cw)
        cr.accept(cv, ClassReader.EXPAND_FRAMES)
        return cw.toByteArray()
    }

    private static class ServiceProviderClassVisitor extends ClassVisitor {
        ServiceProviderClassVisitor(int api, ClassVisitor cv) {
            super(api, cv)
        }

        @Override
        MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions)
            if (name == GENERATE_TO_METHOD_NAME) {
                mv = new RegisterMethodVisitor(Opcodes.ASM6, mv)
            }
            return mv
        }
    }

    private static class RegisterMethodVisitor extends MethodVisitor {
        RegisterMethodVisitor(int api, MethodVisitor mv) {
            super(api, mv)
        }

        @Override
        void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)) {
                mProvidersMap.each { Map.Entry<String, Set<String>> entry ->
                    String key = entry.key
                    Set<String> providersSet = entry.value
                    if (providersSet != null && providersSet.size() > 0) {
                        providersSet.each { provider ->
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "com/sunfusheng/spi/api/ProvidersPool", "registry", "Lcom/sunfusheng/spi/api/ProvidersRegistry;");
                            mv.visitLdcInsn(key);
                            mv.visitLdcInsn(provider);
                            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "com/sunfusheng/spi/api/ProvidersRegistry", "register", "(Ljava/lang/String;Ljava/lang/String;)V", true);
                        }
                    }
                }
            }
            super.visitInsn(opcode)
        }
    }
}
