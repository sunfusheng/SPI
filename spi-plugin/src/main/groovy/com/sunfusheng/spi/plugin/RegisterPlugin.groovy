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
        if (project.plugins.hasPlugin(AppPlugin.class)) {
            println '【spi-plugin】is applied'
            project.extensions.findByType(AppExtension.class).registerTransform(new RegisterTransform())
        }
    }
}
