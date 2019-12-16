
import com.android.build.gradle.AppExtension
import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.gradle.api.Project
class MyInjectByJavassit {
    private static final ClassPool mClassPool = ClassPool.getDefault()

    static void injectToast(String path, Project project) {
        // 加入当前路径
        mClassPool.appendClassPath(path)
        // project.android.bootClasspath 加入android.jar，不然找不到android相关的所有类
        mClassPool.appendClassPath(project.android.bootClasspath[0].toString())
        // 引入android.os.Bundle包，因为onCreate方法参数有Bundle
        mClassPool.importPackage('android.os.Bundle')

        File dir = new File(path)
        if (dir.isDirectory()) {
            // 遍历文件夹
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath
                println("filePath: $filePath")
                println("fileName: $file.name")

                if (file.name == 'TestActivity.class') {
                    // 获取Class
                    // 这里的MainActivity就在app模块里
                    CtClass ctClass = mClassPool.getCtClass('com.wynne.knowledge.home.TestActivity')
                    println("ctClass: $ctClass")

                    // 解冻
                    if (ctClass.isFrozen()) {
                        ctClass.defrost()
                    }

                    // 获取Method
                    CtMethod ctMethod = ctClass.getDeclaredMethod('initView')
                    println("ctMethod: $ctMethod")

                    String toastStr = """ android.widget.Toast.makeText(this,"我是被插入的Toast代码~!!",android.widget.Toast.LENGTH_SHORT).show();  
                                      """

                    // 方法尾插入
                    ctMethod.insertAfter(toastStr)
                    ctClass.writeFile(path)
                    ctClass.detach() //释放
                }
            }
        }

    }
}