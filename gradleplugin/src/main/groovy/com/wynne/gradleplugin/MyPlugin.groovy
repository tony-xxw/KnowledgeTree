package com.wynne.gradleplugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // 获取Android扩展
        def android = project.extensions.getByType(AppExtension)
        def transform = new MyTransform(project)
//        // 注册Transform，其实就是添加了Task
        android.registerTransform(transform)
    }

}
