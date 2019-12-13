package com.wynne.gradleplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task("testPlugin").doLast {
            println("Model 自定义插件 测试")
        }
//        project.extensions.create("pluginSrc", MyExtension)
//        project.task("testPlugin").doLast {
//            println project.pluginSrc.message
//        }
    }

}
