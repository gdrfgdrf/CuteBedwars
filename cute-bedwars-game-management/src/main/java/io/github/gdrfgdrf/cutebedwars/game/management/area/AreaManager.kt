package io.github.gdrfgdrf.cutebedwars.game.management.area

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IAreaManager
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cuteframework.utils.FileUtils
import io.github.gdrfgdrf.cuteframework.utils.jackson.JacksonUtils
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File

@ServiceImpl("area_manager", needArgument = true)
class AreaManager(argumentSet: ArgumentSet): IAreaManager {
    val area: Area = argumentSet.args[0] as Area

    val context: AreaContext
    private var file: File? = null

    init {
        if (area.id == null) {
            area.id = YitIdHelper.nextId()
        }
        if (area.name.isNullOrEmpty()) {
            area.name = "temp_name_${area.id}"
        }

        val folder = File(IConstants.AREA_FOLDER())
        if (!folder.exists()) {
            folder.mkdirs()
        }
        file = File(folder, area.id.toString() + ".json")

        context = AreaContext(this)
        area.games.forEach(context::addGame)
    }

    constructor(area: Area): this(ArgumentSet(arrayOf(area)))

    override fun area(): Area {
        return area
    }

    override fun save() {
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

    fun autoSave() {

    }
}