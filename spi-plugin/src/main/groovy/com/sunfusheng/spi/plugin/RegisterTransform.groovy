package com.sunfusheng.spi.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils

/**
 * @author by sunfusheng on 2019/3/19.
 */
class RegisterTransform extends Transform {
    @Override
    String getName() {
        return 'SPIRegisterTransform'
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        long startTime = System.currentTimeMillis()
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        transformInvocation.inputs.each { TransformInput transformInput ->
            transformInput.jarInputs.each { JarInput jarInput ->
                File srcFile = jarInput.file
                File destFile = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                if (ScanUtil.shouldProcessJarOrDir(jarInput.name)) {
                    ScanUtil.scanJar(srcFile, destFile)
                }
                FileUtils.copyFile(srcFile, destFile)
            }

            transformInput.directoryInputs.each { DirectoryInput dirInput ->
                File srcFile = dirInput.file
                def destDir = outputProvider.getContentLocation(dirInput.name, dirInput.contentTypes, dirInput.scopes, Format.DIRECTORY)
                if (ScanUtil.shouldProcessJarOrDir(srcFile.name)) {
                    srcFile.eachFileRecurse { File file ->
                        if (ScanUtil.shouldProcessFile(file.name)) {
                            ScanUtil.scanFile(file)
                        }
                    }
                }
                FileUtils.copyDirectory(srcFile, destDir)
            }
        }
        CodeGeneratorUtil.generateRegisterCode()
        println '【SPI】time used: ' + (System.currentTimeMillis() - startTime) + 'ms, classes scanned: ' + ScanUtil.classesScanned
        ScanUtil.classesScanned = 0
    }
}
