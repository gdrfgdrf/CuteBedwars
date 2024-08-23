package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("commands_enum")
enum class Commands(
    val string: String,
    val onlyPlayer: Boolean = true,
    val argsRange: IntRange,
    val permissions: Permissions,
    val params: Array<IParam>? = null
): ICommands {
    ROOT("cbw", false, 0..Int.MAX_VALUE, Permissions.ROOT),
    HELP("help", false, 0..0, Permissions.HELP),
    RELOAD("reload", false, 0..0, Permissions.RELOAD),
    QUERY_DESCRIPTION("query-description", false, 1..1, Permissions.QUERY_DESCRIPTION,
        arrayOf(IParam.get("DESCRIPTION"))),

    CREATE_AREA("create-area", false, 1..1, Permissions.CREATE_AREA,
        arrayOf(IParam.get("AREA_NAME")))

    ;

    override fun name_(): String = name
    override fun string(): String = string
    override fun onlyPlayer(): Boolean = onlyPlayer
    override fun argsRange(): IntRange = argsRange
    override fun permissions(): IPermissions = permissions
    override fun params(): Array<IParam>? = params

    override fun get(): String {
        if (this == ROOT) {
            return "/cbw"
        }
        if (params.isNullOrEmpty()) {
            return "${ROOT.get()} $string"
        }
        return "${ROOT.get()} $string ${getWithParams()}"
    }

    private fun getWithParams(): String {
        if (params.isNullOrEmpty()) {
            return get()
        }
        val stringBuilder = StringBuilder()

        params.forEachIndexed { index, it ->
            if (index != params.size - 1) {
                stringBuilder.append(it.get()).append(" ")
            } else {
                stringBuilder.append(it.get())
            }
        }

        return stringBuilder.toString()
    }

    companion object {
        private val map = HashMap<String, Commands>()
        init {
            entries.toTypedArray().forEach {
                map[it.string] = it
            }
        }

        fun get(command: String): Commands? {
            return map[command]
        }
    }
}