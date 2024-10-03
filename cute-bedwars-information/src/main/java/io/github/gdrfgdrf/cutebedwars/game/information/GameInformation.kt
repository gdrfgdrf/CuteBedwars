package io.github.gdrfgdrf.cutebedwars.game.information

import de.tr7zw.nbtapi.NBT
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IGameInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.Generator
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.TeamColor
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("game_information")
object GameInformation : IGameInformation {
    override fun convert(sender: CommandSender, gameContext: IGameContext): List<ITranslationAgent> =
        localizationScope(sender) {
            val game = gameContext.game()

            val identifierMessages = arrayListOf<ITranslationAgent>()

            val properties = getGameProperties(game)
            val propertyMessages = arrayListOf<ITranslationAgent>()

            val commonMessages = arrayListOf<ITranslationAgent>()

            identifierMessages.add(
                message(AreaManagementLanguage.GAME_ID_IS)
                    .format0(game.id)
            )
            identifierMessages.add(
                message(AreaManagementLanguage.GAME_AREA_ID_IS)
                    .format0(game.areaId)
            )
            identifierMessages.add(
                message(AreaManagementLanguage.GAME_NAME_IS)
                    .format0(game.name)
            )

            propertyMessages.add(message(AreaManagementLanguage.GAME_PROPERTY_IS))
            properties.forEach { (key, value) ->
                propertyMessages.add(
                    message(AreaManagementLanguage.GAME_PROPERTY_FORMAT)
                        .format0(key, value)
                )
            }

            commonMessages.apply {
                game.region?.let { region ->
                    RegionInformation.convert(sender, region)?.let {
                        add(message(AreaManagementLanguage.GAME_REGION_IS))
                        add(it)
                    }
                }
                game.waitingRoom?.let { waitingRoom ->
                    if (waitingRoom.region == null && waitingRoom.spawnpointCoordinate == null) {
                        return@let
                    }

                    add(message(AreaManagementLanguage.GAME_WAITING_ROOM_IS))

                    waitingRoom.region?.let { region ->
                        RegionInformation.convert(sender, region)?.let {
                            add(message(AreaManagementLanguage.WAITING_ROOM_REGION_IS))
                            add(it)
                        }
                    }
                    waitingRoom.spawnpointCoordinate?.let { coordinate ->
                        val coordinateMessage = message(CommonLanguage.COORDINATE_FULL)
                            .format0(coordinate.x, coordinate.y, coordinate.z)
                            .toString()

                        add(message(AreaManagementLanguage.WAITING_ROOM_SPAWNPOINT_COORDINATE)
                            .format0(coordinateMessage))
                    }
                }

//                val generatorConvertFunction = { generator: Generator ->
//                    val itemStringBuilder = StringBuilder()
//                    val levelStringBuilder = StringBuilder()
//
//                    if (!generator.products.isNullOrEmpty()) {
//                        val products = generator.products
//
//                        products.forEachIndexed { index, item ->
//                            val nbt = NBT.parseNBT(item.nbt)
//                            val itemStack = NBT.itemStackFromNBT(nbt) ?: return@forEachIndexed
//                            val itemMeta = itemStack.itemMeta
//                            val name = if (itemMeta.hasDisplayName()) {
//                                itemMeta.displayName
//                            } else {
//                                if (itemMeta.hasLocalizedName()) {
//                                    itemMeta.localizedName
//                                } else {
//                                    itemStack.data.itemType.name
//                                }
//                            }
//
//                            if (index >= products.size - 1) {
//                                val string = message(AreaManagementLanguage.ITEM_FORMAT_END)
//                                    .format(name)
//                                    .toString()
//                                itemStringBuilder.append(string)
//                                return@forEachIndexed
//                            }
//                            val string = message(AreaManagementLanguage.ITEM_FORMAT)
//                                .format(name)
//                                .toString()
//                            itemStringBuilder.append(string)
//                        }
//                    } else {
//                        itemStringBuilder.append(message(CommonLanguage.NONE).toString())
//                    }
//
//                    if (!generator.levels.isNullOrEmpty()) {
//                        val levels = generator.levels
//
//                        levels.forEachIndexed { index, level ->
//                            if (index >= levels.size - 1) {
//                                val string = message(AreaManagementLanguage.GENERATOR_LEVEL_FORMAT_END)
//                                    .format(level.order)
//                                    .toString()
//                                levelStringBuilder.append(string)
//                                return@forEachIndexed
//                            }
//                            val string = message(AreaManagementLanguage.GENERATOR_LEVEL_FORMAT)
//                                .format(level.order)
//                                .toString()
//                            levelStringBuilder.append(string)
//                        }
//                    } else {
//                        levelStringBuilder.append(message(CommonLanguage.NONE).toString())
//                    }
//
//                    add(message(AreaManagementLanguage.GENERATOR_FORMAT)
//                        .format(generator.displayName, itemStringBuilder.toString(), levelStringBuilder.toString()))
//                }

                game.generatorGroups?.let {
                    if (it.isEmpty()) {
                        return@let
                    }

                    add(message(AreaManagementLanguage.GAME_GENERATOR_GROUPS_IS))

                    it.forEach { generatorGroup ->
                        add(message(AreaManagementLanguage.GAME_GENERATOR_GROUP_FORMAT)
                            .format0(generatorGroup.displayName))
                    }
                }

//                game.secondaryGenerators?.let {
//                    if (it.isEmpty()) {
//                        return@let
//                    }
//
//                    add(message(AreaManagementLanguage.GAME_SECONDARY_GENERATORS_IS))
//
//                    it.forEach { generator ->
//                        generatorConvertFunction(generator)
//                    }
//                }
//                game.tertiaryGenerators?.let {
//                    if (it.isEmpty()) {
//                        return@let
//                    }
//
//                    add(message(AreaManagementLanguage.GAME_TERTIARY_GENERATORS_IS))
//
//                    it.forEach { generator ->
//                        generatorConvertFunction(generator)
//                    }
//                }
                game.teams?.let {
                    if (it.isEmpty()) {
                        return@let
                    }

                    add(message(AreaManagementLanguage.GAME_TEAMS_IS))

                    it.forEach { team ->
                        val color = if (team.color != null) {
                            team.color
                        } else {
                            TeamColor.RESET
                        }
                        val name = team.name

                        add(message(AreaManagementLanguage.TEAM_FORMAT)
                            .format0("§${color.code}$name"))
                    }
                }
            }

            arrayListOf<ITranslationAgent>().apply {
                addAll(identifierMessages)
                addAll(propertyMessages)
                addAll(commonMessages)
            }
        }


    private fun getGameProperties(game: Game): Map<String, Any> {
        val result = LinkedHashMap<String, Any>()

        result["status"] = game.status
        result["min-player"] = game.minPlayer
        result["max-player"] = game.maxPlayer
        result["spectator-spawnpoint-coordinate"] = game.spectatorSpawnpointCoordinate

        return result
    }

}