package com.sunfusheng.spi.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

/**
 * @author by sunfusheng on 2019/3/19.
 */
class RegisterTransform extends Transform {
    Project mProject
    RegisterCodeGenerator mCodeGenerator

    RegisterTransform(Project project, RegisterCodeGenerator codeGenerator) {
        this.mProject = project
        this.mCodeGenerator = codeGenerator
    }

    @Override
    String getName() {
        return 'RegisterTransform'
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

        TransformOutputProvider outputProvider = transformInvocation.outputProvider

        transformInvocation.inputs.each { TransformInput input ->
            input.jarInputs.each { JarInput jarInput ->
                def destFile = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                if (ScanUtil.shouldProcessJarOrDir(jarInput.name)) {
                    println '【spi-plugin】jarName:' + jarInput.name + ' jarPath:' + jarInput.file.absolutePath
                    ScanUtil.scanJar(jarInput.file)
                }
                FileUtils.copyFile(jarInput.file, destFile)
            }

            input.directoryInputs.each { DirectoryInput directoryInput ->
                if (ScanUtil.shouldProcessJarOrDir(directoryInput.name)) {
                    println '【spi-plugin】dirName:' + directoryInput.name + ' dirPath:' + directoryInput.file.absolutePath
                }

                def destDir = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file, destDir)
            }
        }
    }


}
