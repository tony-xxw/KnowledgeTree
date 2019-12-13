import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project

//import com.sun.javafx.geom.transform.BaseTransform

class MyTransform extends Transform {


    private Project mProject

    MyTransform(Project mProject) {
        this.mProject = mProject
    }

    @Override
    String getName() {}

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

        //转换的输入输出
        transformInvocation.inputs.each {
                //遍历文件
            TransformInput input ->
                input.directoryInputs.each {
                    DirectoryInput directoryInput ->

                        MyInjectByJavassit.insertToast(directoryInput.file.absolutePath,mProject)



                }
        }
    }
}