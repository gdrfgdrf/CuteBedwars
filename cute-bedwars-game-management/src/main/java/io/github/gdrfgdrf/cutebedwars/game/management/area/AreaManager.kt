package io.github.gdrfgdrf.cutebedwars.game.management.area

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractAreaCommitStorage
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cuteframework.utils.FileUtils
import io.github.gdrfgdrf.cuteframework.utils.jackson.JacksonUtils
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File

@ServiceImpl("area_manager", needArgument = true)
class AreaManager(argumentSet: ArgumentSet) : IAreaManager {
    private val area = argumentSet.args[0] as Area
    private val context: IAreaContext
    private var file: File? = null

    private val commitStorage: AbstractAreaCommitStorage

    init {
        if (area.id == null) {
            val id = YitIdHelper.nextId()
            "Set an id: $id to an area".logInfo()
            area.id = id
        }
        if (area.name.isNullOrEmpty()) {
            "The area (id: ${area.id})'s name is null, setting to \"temp_name_${area.id}\"".logInfo()
            area.name = "temp_name_${area.id}"
        }

        val folder = File(IConstants.areaFolder())
        if (!folder.exists()) {
            folder.mkdirs()
        }
        file = File(folder, area.id.toString() + ".json")

        context = AreaContext(this)

        "Creating the commit storage for an area id: ${area.id}, name: ${area.name}".logInfo()
        commitStorage = AbstractAreaCommitStorage.new(
            IConstants.areaFolder() + area.id + "/" + "commits"
        )
    }

    override fun area(): Area = area
    override fun context(): IAreaContext = context
    override fun commitStorage(): AbstractAreaCommitStorage = commitStorage

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


}