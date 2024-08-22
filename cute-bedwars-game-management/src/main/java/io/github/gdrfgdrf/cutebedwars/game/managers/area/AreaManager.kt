package io.github.gdrfgdrf.cutebedwars.game.managers.area

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Area
import io.github.gdrfgdrf.cutebedwars.commons.Constants
import io.github.gdrfgdrf.cuteframework.utils.FileUtils
import io.github.gdrfgdrf.cuteframework.utils.jackson.JacksonUtils
import java.io.File

class AreaManager(val area: Area) {
    val context: AreaContext
    private var file: File? = null

    init {
        if (area.id == null) {
            area.id = YitIdHelper.nextId()
        }
        if (area.name.isNullOrEmpty()) {
            area.name = "temp_name_${area.id}"
        }

        val folder = File(Constants.AREA_FOLDER)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        file = File(folder, area.id.toString() + ".json")

        context = AreaContext(this)
        area.games.forEach(context::addGame)
    }

    fun save() {
        if (file == null) {
            throw IllegalArgumentException("file is required")
        }

        if (!file!!.exists()) {
            if (file!!.parentFile?.exists() == false) {
                file!!.parentFile?.mkdirs()
            }
            file!!.createNewFile()
        }
        if (!file!!.exists()) {
            throw IllegalStateException("Cannot create new file $file")
        }

        val string = JacksonUtils.writeJsonString(area)
        val writer = FileUtils.getWriter(file)
        writer.write(string)
        writer.close()
    }
}