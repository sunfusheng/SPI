package com.sunfusheng.spi.plugin

import org.gradle.api.Project;

/**
 * @author by sunfusheng on 2019/3/19
 */
class RegisterCodeGenerator {
    static final String GENERATE_TO_CLASS_NAME = 'com/sunfusheng/spi/api/ServiceProvider.class'
    static final String GENERATE_TO_METHOD_NAME = 'register'

    Project mProject
    static File mServiceProviderFile
    static List<String> mProvidersList = new ArrayList<>()

    RegisterCodeGenerator(Project project) {
        this.mProject = project
    }

    static void insertRegisterCode() {
        if (mProvidersList != null && !mProvidersList.isEmpty() &&
                mServiceProviderFile != null && mServiceProviderFile.name.endsWith(".jar")) {

        }
    }
}
