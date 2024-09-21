package io.github.gdrfgdrf.cutebedwars.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object RegionInformation {
    fun convert(sender: CommandSender, region: Region): ILocalizationMessage? =
        localizationScope(sender) {
            val result = Type.calculate(region) ?: return@localizationScope null
            val type = result.type
            val message = type.message()

            val resultMessage = if (type == Type.REGION_VALIDATION) {
                message(message)
            } else {
                val top = result.top
                val bottom = result.bottom

                if (top == null || bottom == null) {
                    return@localizationScope null
                }

                val topMessage = message(CommonLanguage.COORDINATE_FULL)
                    .format(top.x.toString(), top.y.toString(), top.z.toString())
                    .toString()
                val bottomMessage = message(CommonLanguage.COORDINATE_FULL)
                    .format(bottom.x.toString(), bottom.y.toString(), bottom.z.toString())
                    .toString()

                when (type) {
                    Type.REGION_1 -> message(message)
                        .format(topMessage, bottomMessage)
                    Type.REGION_2 -> message(message)
                        .format(topMessage, bottomMessage)
                    Type.REGION_3 -> {
                        val length = messageLength("§", topMessage)

                        message(
                            fillBlank(
                                message(message).toString(), length, 2
                            )
                        ).format(topMessage, bottomMessage)
                    }
                    Type.REGION_4 -> {
                        val length = messageLength("§", bottomMessage)

                        message(
                            fillBlank(
                                message(message).toString(), length, 2
                            )
                        ).format(topMessage, bottomMessage)
                    }
                    else -> null
                }
            }

            resultMessage
        }

    private fun messageLength(symbol: String, message: String): Int {
        return message
            .replace(symbol + "0", "")
            .replace(symbol + "1", "")
            .replace(symbol + "2", "")
            .replace(symbol + "3", "")
            .replace(symbol + "4", "")
            .replace(symbol + "5", "")
            .replace(symbol + "6", "")
            .replace(symbol + "7", "")
            .replace(symbol + "8", "")
            .replace(symbol + "9", "")
            .replace(symbol + "a", "")
            .replace(symbol + "b", "")
            .replace(symbol + "c", "")
            .replace(symbol + "d", "")
            .replace(symbol + "e", "")
            .replace(symbol + "f", "")
            .replace(symbol + "k", "")
            .replace(symbol + "l", "")
            .replace(symbol + "m", "")
            .replace(symbol + "n", "")
            .replace(symbol + "o", "")
            .replace(symbol + "r", "")
            .length
    }

    private fun fillBlank(string: String, length: Int, extraBlankLengthInStartWith: Int): String {
        val blank = StringBuilder()
        for (i in 0 until length) {
            blank.append(" ")
        }

        val split = string.split("\n")
        val result = StringBuilder()
        split.forEachIndexed { index, line ->
            if (index >= split.size - 1) {
                if (line.startsWith("%s")) {
                    val extraBlank = StringBuilder()
                    for (i in 0 until extraBlankLengthInStartWith) {
                        extraBlank.append(" ")
                    }

                    result.append(extraBlank).append(line)
                    return@forEachIndexed
                }

                result.append(blank).append(line)
                return@forEachIndexed
            }
            if (line.startsWith("%s")) {
                val extraBlank = StringBuilder()
                for (i in 0 until extraBlankLengthInStartWith) {
                    extraBlank.append(" ")
                }

                result.append(extraBlank).append(line).append("\n")
                return@forEachIndexed
            }
            result.append(blank).append(line).append("\n")
        }

        return result.toString()
    }


    enum class Type(val message: () -> LanguageString, val test: (Region) -> CalculateResult) {
        REGION_VALIDATION(CommonLanguage::REGION_ERROR, { region ->
            val first = region.firstCoordinate
            val second = region.secondCoordinate

            val result = CalculateResult(REGION_VALIDATION, true, null, null)

            // compare first to itself
            if (first.x == first.y) {
                result.result = false
            }
            if (first.x == first.z) {
                result.result = false
            }
            if (first.y == first.z) {
                result.result = false
            }

            // compare second to itself
            if (second.x == second.y) {
                result.result = false
            }
            if (second.x == second.z) {
                result.result = false
            }
            if (second.y == second.z) {
                result.result = false
            }

            // compare first's x to second
            if (first.x == second.x) {
                result.result = false
            }
            if (first.x == second.y) {
                result.result = false
            }
            if (first.x == second.z) {
                result.result = false
            }

            // compare first's y to second
            if (first.y == second.x) {
                result.result = false
            }
            if (first.y == second.y) {
                result.result = false
            }
            if (first.y == second.z) {
                result.result = false
            }

            // compare first's z to second
            if (first.z == second.x) {
                result.result = false
            }
            if (first.z == second.y) {
                result.result = false
            }
            if (first.z == second.z) {
                result.result = false
            }

            result
        }),
        REGION_1(CommonLanguage::REGION_1, { region ->
            val first = region.firstCoordinate
            val second = region.secondCoordinate
            val result = CalculateResult(REGION_1, true, null, null)

            if (first.y == second.y) {
                result.result = false
            } else {
                val top: Coordinate
                val bottom: Coordinate

                if (first.y > second.y) {
                    top = first
                    bottom = second
                } else {
                    top = second
                    bottom = first
                }

                result.top = top
                result.bottom = bottom

                if (top.x >= bottom.x ||
                    top.z >= bottom.z) {
                    result.result = false
                }
            }

            result
        }),
        REGION_2(CommonLanguage::REGION_2, { region ->
            val first = region.firstCoordinate
            val second = region.secondCoordinate
            val result = CalculateResult(REGION_2, true, null, null)

            if (first.y == second.y) {
                result.result = false
            } else {
                val top: Coordinate
                val bottom: Coordinate

                if (first.y > second.y) {
                    top = first
                    bottom = second
                } else {
                    top = second
                    bottom = first
                }

                result.top = top
                result.bottom = bottom

                if (top.x <= bottom.x || top.z >= bottom.z) {
                    result.result = false
                }
            }

            result
        }),
        REGION_3(CommonLanguage::REGION_3, { region ->
            val first = region.firstCoordinate
            val second = region.secondCoordinate
            val result = CalculateResult(REGION_3, true, null, null)

            if (first.y == second.y) {
                result.result = false
            } else {
                val top: Coordinate
                val bottom: Coordinate

                if (first.y > second.y) {
                    top = first
                    bottom = second
                } else {
                    top = second
                    bottom = first
                }

                result.top = top
                result.bottom = bottom

                if (top.x >= bottom.x || top.z <= bottom.z) {
                    result.result = false
                }
            }

            result
        }),
        REGION_4(CommonLanguage::REGION_4, { region ->
            val first = region.firstCoordinate
            val second = region.secondCoordinate
            val result = CalculateResult(REGION_4, true, null, null)

            if (first.y == second.y) {
                result.result = false
            } else {
                val top: Coordinate
                val bottom: Coordinate

                if (first.y > second.y) {
                    top = first
                    bottom = second
                } else {
                    top = second
                    bottom = first
                }

                result.top = top
                result.bottom = bottom

                if (top.x <= bottom.x || top.z <= bottom.z) {
                    result.result = false
                }
            }

            result
        })
        ;

        companion object {
            fun calculate(region: Region): CalculateResult? {
                var result: CalculateResult?

                result = REGION_VALIDATION.test(region)
                if (!result.result) {
                    return result
                }

                result = REGION_1.test(region)
                if (result.result) {
                    return result
                }

                result = REGION_2.test(region)
                if (result.result) {
                    return result
                }

                result = REGION_3.test(region)
                if (result.result) {
                    return result
                }

                result = REGION_4.test(region)
                if (result.result) {
                    return result
                }

                return null
            }
        }

        class CalculateResult(var type: Type, var result: Boolean = true, var top: Coordinate?, var bottom: Coordinate?) {

        }
    }
}