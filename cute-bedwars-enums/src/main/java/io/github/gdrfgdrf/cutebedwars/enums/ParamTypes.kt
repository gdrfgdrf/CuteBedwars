package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("param_types_enum")
enum class ParamTypes : IParamTypes {
    NOT_BLANK_STRING {
        override fun validate(any: Any): Boolean {
            if (any !is String) {
                return false
            }
            return any.isNotBlank()
        }
    };

    override fun name_(): String = name
}