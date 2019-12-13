import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.gradle.api.Project

class MyInjectByJavassit {
    private static final ClassPool mClassPool = ClassPool.getDefault()

    static void insertToast(String path, Project project) {
        //加入当前路径
        mClassPool.appendClassPath(path)

        mClassPool.appendClassPath(project.android.bootClasspath[0].toString())

        mClassPool.importPackage("android.os.Bundle")

        File dir = new File(path)

        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath

                println("filePath :$filePath")

                if (file.name = "MainActivity.class") {
                    CtClass ctClass = mClassPool.getCtClass("com.wynne.weekly.WeeklyActivity")
                    println("ctClass: $ctClass")

                    if (ctClass.isFrozen()) {
                        ctClass.defrost()
                    }

                    CtMethod ctMethod = ctClass.getDeclaredMethod("onCreate")
                    println("ctMethod $ctMethod")

                    String toastStr = """android.widget.Toast.makeText(this,"我是被插入的Toast代码~!!",android.widget.Toast.LENGTH_SHORT).show();"""

                    ctMethod.insertAfter(toastStr)
                    ctClass.writeFile(path)
                    ctClass.detach()
                }
            }
        }

    }

}