package com.wynne.advanced.hot

import android.content.Context
import android.util.Log
import dalvik.system.BaseDexClassLoader
import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader
import java.io.File
import java.lang.Exception
import java.lang.reflect.Array

object FixDexUtils {
    const val DEX_SUFFIX = ".dex"
    const val APK_SUFFIX = ".apk"
    const val JAR_SUFFIX = ".jar"
    const val ZIP_SUFFIX = ".zip"
    const val DEX_DIR = "odex"
    const val OPTIMIZED_DEX_DIR = "optimize_dex"

    var loadedDex = mutableSetOf<File>()

    init {
        loadedDex.clear()
    }

    fun loadFixedDex(context: Context) {
        loadFixedDex(context, null)
    }

    fun loadFixedDex(context: Context?, pathFilesDir: File?) {
        context?.let {
            val fileDir = pathFilesDir ?: File(it.filesDir, DEX_DIR)
            val listFiles = fileDir.listFiles()
            listFiles.forEach { file ->
                if (file.name.startsWith("classes")
                        && file.name.endsWith(DEX_SUFFIX)
                        || file.name.endsWith(APK_SUFFIX)
                        || file.name.endsWith(JAR_SUFFIX)
                        || file.name.endsWith(ZIP_SUFFIX)) {
                    Log.d("XXW", "111111111")
                    loadedDex.add(file)
                }
            }
            doDexInject(it, loadedDex)
        }
    }

    private fun doDexInject(appContext: Context, loadedDex: MutableSet<File>) {
        val optimizeDir = appContext.filesDir.absolutePath + File.separator + OPTIMIZED_DEX_DIR
        val fopt = File(optimizeDir)
        if (!fopt.exists()) {
            fopt.mkdir()
        }

        try {
            val pathLoader = appContext.classLoader as PathClassLoader
            loadedDex.forEach { dex ->
                val dexClassLoader = DexClassLoader(dex.absolutePath, fopt.absolutePath, null, pathLoader)
                val dexPathList = getPathList(dexClassLoader)
                val pathPathList = getPathList(pathLoader)
                val leftDexElement = getDexElements(dexPathList)
                val rightDexElement = getDexElements(pathPathList)

                val dexElements = combineArray(leftDexElement, rightDexElement)
                val pathList = getPathList(pathLoader)
                setField(pathList, pathList.javaClass, "dexElements", dexElements)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setField(obj: Any, cl: Class<Any>, field: String, value: Any) {
        val declaredField = cl.getDeclaredField(field)
        declaredField.isAccessible = true
        declaredField.set(obj, value)
    }

    private fun combineArray(arrayLhs: Any, arrayRhs: Any): Any {
        val componentType = arrayLhs.javaClass.componentType
        val i = Array.getLength(arrayLhs)
        val j = Array.getLength(arrayRhs)
        val k = i + j
        val result = Array.newInstance(componentType, k)
        System.arraycopy(arrayLhs, 0, result, 0, i)
        System.arraycopy(arrayRhs, 0, result, i, j)
        return result

    }

    private fun getDexElements(pathList: Any): Any {
        return getFile(pathList, pathList.javaClass, "dexElements")
    }

    private fun getPathList(obj: BaseDexClassLoader): Any {
        return getFile(obj = obj, cl = Class.forName("dalvik.system.BaseDexClassLoader"), field = "pathList")
    }

    private fun getFile(obj: Any, cl: Class<*>, field: String): Any {
        val localFile = cl.getDeclaredField(field)
        localFile.isAccessible = true
        return localFile.get(obj)
    }
}