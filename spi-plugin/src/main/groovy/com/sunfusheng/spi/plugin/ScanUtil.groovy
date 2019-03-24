package com.sunfusheng.spi.plugin

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

import java.util.jar.JarEntry
import java.util.jar.JarFile


/**
 * @author by sunfusheng on 2019/3/19
 */
class ScanUtil {
    static final String PACKAGE_OF_PROVIDERS = 'com/sunfusheng/spi/providers/'

    static boolean shouldProcessJarOrDir(String name) {
        return !name.startsWith('com.android.support') && !name.startsWith('android.arch')
    }

    static void scanJar(File file) {
        if (file) {
            def jarFile = new JarFile(file)
            Enumeration<JarEntry> enumeration = jarFile.entries()
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = enumeration.nextElement()
                String entryName = jarEntry.getName()
                if (entryName.startsWith(PACKAGE_OF_PROVIDERS)) {
                    println '【spi-plugin】PACKAGE_OF_PROVIDERS entryName:' + entryName
                    InputStream inputStream = jarFile.getInputStream(jarEntry)
                    scanInputStream(inputStream)
                    inputStream.close()
                } else if (entryName.startsWith(RegisterCodeGenerator.GENERATE_TO_CLASS_NAME)) {
                    println '【spi-plugin】GENERATE_TO_CLASS_NAME fileName:' + file.name
                    RegisterCodeGenerator.mServiceProviderFile = file
                }
            }
            jarFile.close()
        }
    }

    static void scanFile(File file) {
        scanInputStream(new FileInputStream(file))
    }

    static void scanInputStream(InputStream inputStream) {
        ClassReader cr = new ClassReader(inputStream)
        ClassWriter cw = new ClassWriter(cr, 0)
        ScanClassVisitor cv = new ScanClassVisitor(Opcodes.ASM6, cw)
        cr.accept(cv, ClassReader.EXPAND_FRAMES)
        inputStream.close()
    }

    static class ScanClassVisitor extends ClassVisitor {
        ScanClassVisitor(int api, ClassVisitor cv) {
            super(api, cv)
        }

        void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces)
            println '【spi-plugin】className:' + name
            if (name != null && name.startsWith(PACKAGE_OF_PROVIDERS)) {
                RegisterCodeGenerator.mProvidersList.add(name)
            }
        }
    }
}
