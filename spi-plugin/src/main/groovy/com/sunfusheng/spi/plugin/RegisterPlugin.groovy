package com.sunfusheng.spi.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author by sunfusheng on 2019/3/19.
 */
class RegisterPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def isApp = project.plugins.hasPlugin(AppPlugin.class)
        if (isApp) {
            println '【spi-plugin】is applied'
            def android = project.extensions.findByType(AppExtension.class)
            RegisterCodeGenerator codeGenerator = new RegisterCodeGenerator()
            android.registerTransform(new RegisterTransform(project, codeGenerator))
        }
    }
}
