package io.github.gdrfgdrf.cutebedwars.game.management.area

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractAreaCommitStorage
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IFiles
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IJsons
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File

@ServiceImpl("area_manager", needArgument = true)
class AreaManager(argumentSet: ArgumentSet) : IAreaManager {
    override val area = argumentSet.args[0] as Area
    override var context: IAreaContext? = null
    override var commitStorage: AbstractAreaCommitStorage? = null
    override var initialized = false

    private var file: File? = null

    private fun check() {
        if (!initialized) {
            throw IllegalStateException("this area manager is not initialized")
        }
    }

    override fun init() {
        if (initialized) {
            throw IllegalStateException("this area manager is initialized")
        }
        initialized = true

        if (area.id == null) {
            val id = YitIdHelper.nextId()
            "Set an id: $id to an area".logInfo()
            area.id = id
        }
        if (area.name.isNullOrEmpty()) {
            "The area (id: ${area.id})'s name is null, setting to \"temp_name_${area.id}\"".logInfo()
            area.name = "temp_name_${area.id}"
        }

        val folder = File(IConstants["AREA_FOLDER"])
        if (!folder.exists()) {
            folder.mkdirs()
        }
        file = File(folder, area.id.toString() + ".json")

        context = AreaContext(this)

        "Creating the commit storage for an area id: ${area.id}, name: ${area.name}".logInfo()
        commitStorage = AbstractAreaCommitStorage.new(
            IConstants["AREA_FOLDER"] + area.id + "/" + "commits"
        )

        save()
    }

    override fun save() {
        check()
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

        val string = IJsons.instance().write(area)
        val writer = IFiles.instance().writer(file!!)
        writer.write(string)
        writer.close()
    }


}