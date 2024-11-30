package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IConfigs
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.io.File

@ServiceImpl("configs")
object Configs : IConfigs {
    override fun <T : Any> load(fileName: String, clazz: Class<T>): T {
        val file = if (clazz.name == "io.github.gdrfgdrf.cutebedwars.commons.Config") {
            File(IConstants["BASE_FOLDER"], fileName)
        } else {
            File(IConstants["ANOTHER_CONFIG_PATH"], fileName)
        }
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }

        if (!file.exists()) {
            file.createNewFile()

            val result = clazz.getDeclaredConstructor().newInstance()

            val resetMethod = clazz.getDeclaredMethod("reset", clazz)
            resetMethod.invoke(null, result)

            save(fileName, result)
            return result
        }

        val result = Jsons.read<T>(file, clazz)
        return result
    }

    override fun save(fileName: String, any: Any) {
        val folder = if (any::class.java.name == "io.github.gdrfgdrf.cutebedwars.commons.Config") {
            File(IConstants["BASE_FOLDER"])
        } else {
            File(IConstants["ANOTHER_CONFIG_PATH"], fileName)
        }
        if (!folder.exists()) {
            folder.mkdirs()
        }

        val file = File(folder, fileName)
        if (!file.exists()) {
            file.createNewFile()
        }

        val writer = Files.writer(file)
        writer.write(Jsons.write(any))
        writer.close()
    }
}