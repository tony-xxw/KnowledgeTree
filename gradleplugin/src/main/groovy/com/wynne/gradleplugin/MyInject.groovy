package com.wynne.gradleplugin

import groovy.io.FileType
import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.gradle.api.Project

/**
 * 通过Javassist 向class注入代码
 */
class MyInject {
    private ClassPool mClassPool = ClassPool.getDefault()
    private String[] clazz
    private String[] methods
    private List<String> mClazzs

    MyInject(Project project) {
        // project.android.bootClasspath 加入android.jar，不然找不到android相关的所有类
        mClassPool.appendClassPath(project.android.bootClasspath[0].toString())
        // 引入android.os.Bundle包，因为onCreate方法参数有Bundle
        mClassPool.importPackage('android.os.Bundle')
        mClassPool.importPackage('android.widget.Toast')
        mClassPool.importPackage('com.wynne.weekly.utils.DetectionUtil')
        mClassPool.importPackage('com.wynne.weekly.utils.DetectionMethod')
        clazz = project.dtcMethods.clazz
        methods = project.dtcMethods.methods
        mClazzs = new LinkedList<>()
        methods.each { methods ->
            def className = methods.substring(0, methods.lastIndexOf("."))
            mClazzs.add(className)

        }
    }

    void injectToast(String path) {
        mClassPool.appendClassPath(path)
        File dir = new File(path)
        if (dir.isDirectory()) {
            // 遍历文件夹
            dir.eachFileRecurse { file ->
                if (file.isFile()) {
                    String filePath = file.absolutePath
                    String className = filePath.substring(filePath.lastIndexOf("classes") + 8, filePath.length() - 6).replace("/", ".")
                    if (clazz.contains(className)) {
                        // 获取Class
                        insertDclCode(className, path)
                    } else if (mClazzs.contains(className)) {
                        insertDclCode(className, path)
                    }
                }
            }
        }
    }


    void insertDclCode(String className, String path) {
        CtClass ctClass = mClassPool.getCtClass(className)
        // 解冻
        if (ctClass.isFrozen()) {
            ctClass.defrost()
        }
        // 获取Method
        CtMethod[] ctMethod = ctClass.getDeclaredMethods()
        //获取每个方法循环插入-
        ctMethod.each { methods ->
            String beforeStr = String.format("DetectionUtil.getInstance().startMethod(new DetectionMethod(\"%s\"));", methods.name)
            String endStr = String.format("DetectionUtil.getInstance().endMethod();")

            println "beforeStr :$beforeStr"
            println "endStr :$endStr"

            methods.insertBefore(beforeStr)
            methods.insertAfter(endStr)
        }
        ctClass.writeFile(path)
        ctClass.detach() //释放
    }
}