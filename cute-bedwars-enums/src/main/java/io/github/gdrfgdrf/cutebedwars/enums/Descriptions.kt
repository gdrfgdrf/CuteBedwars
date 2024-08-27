package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.languages.collect.DescriptionLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import java.util.regex.Pattern

@EnumServiceImpl("descriptions_enum", searcher = "search")
enum class Descriptions(
    val value: () -> LanguageString?,
    val administration: Boolean = false
): IDescriptions {
    DESCRIPTION(DescriptionLanguage::DESCRIPTION),

    LANGUAGE(DescriptionLanguage::LANGUAGE, true),
    WORKER_ID(DescriptionLanguage::WORKER_ID, true),
    DATABASE_IMPL(DescriptionLanguage::DATABASE_IMPL, true),
    ENABLE_DATABASE_LOGGING(DescriptionLanguage::ENABLE_DATABASE_LOGGING, true),
    DATABASE_USERNAME(DescriptionLanguage::DATABASE_USERNAME, true),
    DATABASE_PASSWORD(DescriptionLanguage::DATABASE_PASSWORD, true),
    REQUEST_TIMEOUT(DescriptionLanguage::REQUEST_TIMEOUT, true),
    AREA_AUTO_SAVE_DELAY(DescriptionLanguage::AREA_AUTO_SAVE_DELAY, true),

    AREA(DescriptionLanguage::AREA),
    AREA_ID(DescriptionLanguage::AREA_ID),
    AREA_NAME(DescriptionLanguage::AREA_NAME),
    AREA_DEFAULT_TEMPLATE_ID(DescriptionLanguage::AREA_DEFAULT_TEMPLATE_ID, true),
    AREA_STATUS(DescriptionLanguage::AREA_STATUS, true),
    AREA_WORLD_NAME(DescriptionLanguage::AREA_WORLD_NAME, true),
    AREA_LOBBY_WORLD_NAME(DescriptionLanguage::AREA_LOBBY_WORLD_NAME, true),
    AREA_LOBBY_SPAWNPOINT_COORDINATE(DescriptionLanguage::AREA_LOBBY_SPAWNPOINT_COORDINATE),
    AREA_GAMES(DescriptionLanguage::AREA_GAMES),
    AREA_PROPERTY(DescriptionLanguage::AREA_PROPERTY, true),

    GAME(DescriptionLanguage::GAME),
    GAME_ID(DescriptionLanguage::GAME_ID),
    GAME_AREA_ID(DescriptionLanguage::GAME_AREA_ID),
    GAME_NAME(DescriptionLanguage::GAME_NAME),
    GAME_MIN_PLAYER(DescriptionLanguage::GAME_MIN_PLAYER, true),
    GAME_MAX_PLAYER(DescriptionLanguage::GAME_MAX_PLAYER, true),
    GAME_REGION(DescriptionLanguage::GAME_REGION, true),
    GAME_WAITING_ROOM(DescriptionLanguage::GAME_WAITING_ROOM),
    GAME_SPECTATOR_SPAWNPOINT_COORDINATE(DescriptionLanguage::GAME_SPECTATOR_SPAWNPOINT_COORDINATE),
    GAME_SECONDARY_GENERATORS(DescriptionLanguage::GAME_SECONDARY_GENERATORS),
    GAME_TERTIARY_GENERATORS(DescriptionLanguage::GAME_TERTIARY_GENERATORS),
    GAME_TEAMS(DescriptionLanguage::GAME_TEAMS),

    TEAM(DescriptionLanguage::TEAM),
    TEAM_ID(DescriptionLanguage::TEAM_ID, true),
    TEAM_NAME(DescriptionLanguage::TEAM_NAME),
    TEAM_COLOR(DescriptionLanguage::TEAM_COLOR),
    TEAM_MIN_PLAYER(DescriptionLanguage::TEAM_MIN_PLAYER, true),
    TEAM_MAX_PLAYER(DescriptionLanguage::TEAM_MAX_PLAYER, true),
    TEAM_REGION(DescriptionLanguage::TEAM_REGION, true),
    TEAM_OPERABLE_COORDINATES(DescriptionLanguage::TEAM_OPERABLE_COORDINATES, true),
    TEAM_SPAWNPOINT_COORDINATE(DescriptionLanguage::TEAM_SPAWNPOINT_COORDINATE),
    TEAM_BED_COORDINATE(DescriptionLanguage::TEAM_BED_COORDINATE, true),
    TEAM_VILLAGERS(DescriptionLanguage::TEAM_VILLAGERS),
    TEAM_GENERATOR(DescriptionLanguage::TEAM_GENERATOR),

    WAITING_ROOM(DescriptionLanguage::WAITING_ROOM),
    WAITING_ROOM_GAME_ID(DescriptionLanguage::WAITING_ROOM_GAME_ID, true),
    WAITING_ROOM_REGION(DescriptionLanguage::WAITING_ROOM_REGION, true),
    WAITING_ROOM_SPAWNPOINT_COORDINATE(DescriptionLanguage::WAITING_ROOM_SPAWNPOINT_COORDINATE),

    GENERATOR(DescriptionLanguage::GENERATOR),
    GENERATOR_REGION(DescriptionLanguage::GENERATOR_REGION, true),
    GENERATOR_OPERABLE_COORDINATES(DescriptionLanguage::GENERATOR_OPERABLE_COORDINATES, true),
    GENERATOR_GENERATE_COORDINATE(DescriptionLanguage::GENERATOR_GENERATE_COORDINATE, true),
    GENERATOR_DISPLAY(DescriptionLanguage::GENERATOR_DISPLAY, true),
    GENERATOR_DISPLAY_NAME(DescriptionLanguage::GENERATOR_DISPLAY_NAME, true),
    GENERATOR_PRODUCTS(DescriptionLanguage::GENERATOR_PRODUCTS, true),
    GENERATOR_LEVELS(DescriptionLanguage::GENERATOR_LEVELS, true),
    GENERATOR_PROPERTY(DescriptionLanguage::GENERATOR_PROPERTY, true),

    AUTOMATIC_GENERATOR(DescriptionLanguage::AUTOMATIC_GENERATOR),
    AUTOMATIC_GENERATOR_PROPERTY(DescriptionLanguage::AUTOMATIC_GENERATOR_PROPERTY, true),

    GENERATOR_LEVEL(DescriptionLanguage::GENERATOR_LEVEL, true),
    GENERATOR_LEVEL_ORDER(DescriptionLanguage::GENERATOR_LEVEL_ORDER),
    GENERATOR_LEVEL_GENERATE_COST(DescriptionLanguage::GENERATOR_LEVEL_GENERATE_COST),
    GENERATOR_LEVEL_DISPLAY_NAME(DescriptionLanguage::GENERATOR_LEVEL_DISPLAY_NAME, true),
    GENERATOR_LEVEL_PROPERTY(DescriptionLanguage::GENERATOR_LEVEL_PROPERTY, true),

    AUTOMATIC_GENERATOR_LEVEL(DescriptionLanguage::AUTOMATIC_GENERATOR_LEVEL, true),
    AUTOMATIC_GENERATOR_LEVEL_NEXT_LEVEL_COST(DescriptionLanguage::AUTOMATIC_GENERATOR_LEVEL_NEXT_LEVEL_COST),
    AUTOMATIC_GENERATOR_LEVEL_PROPERTY(DescriptionLanguage::AUTOMATIC_GENERATOR_LEVEL_PROPERTY, true),

    VILLAGER(DescriptionLanguage::VILLAGER),
    VILLAGER_TYPE(DescriptionLanguage::VILLAGER_TYPE),

    STATUS(DescriptionLanguage::STATUS, true),

    COORDINATE(DescriptionLanguage::COORDINATE, true),
    COORDINATE_X(DescriptionLanguage::COORDINATE_X, true),
    COORDINATE_Y(DescriptionLanguage::COORDINATE_Y, true),
    COORDINATE_Z(DescriptionLanguage::COORDINATE_Z, true),

    ITEM(DescriptionLanguage::ITEM, true),
    ITEM_NBT(DescriptionLanguage::ITEM_NBT, true),

    REGION(DescriptionLanguage::REGION, true),
    REGION_FIRST_COORDINATE(DescriptionLanguage::REGION_FIRST_COORDINATE, true),
    REGION_SECOND_COORDINATE(DescriptionLanguage::REGION_SECOND_COORDINATE, true),

    TEMPLATE(DescriptionLanguage::TEMPLATE, true),
    TEMPLATE_ID(DescriptionLanguage::TEMPLATE_ID, true),
    TEMPLATE_NAME(DescriptionLanguage::TEMPLATE_NAME, true),
    TEMPLATE_PROPERTY(DescriptionLanguage::TEMPLATE_PROPERTY, true),

    SEARCH_BY_ID_OR_NAME(DescriptionLanguage::SEARCH_BY_ID_OR_NAME),
    BY_ID(DescriptionLanguage::BY_ID),
    BY_NAME(DescriptionLanguage::BY_NAME),

    VALUE(DescriptionLanguage::VALUE),
    PAGE_INDEX(DescriptionLanguage::PAGE_INDEX)

    ;

    override fun name_(): String = name
    override fun value(): () -> LanguageString? = value
    override fun administration(): Boolean = administration

    companion object {
        @JvmStatic
        fun search(name: String): List<Descriptions> {
            return searchInternal(name).distinct().toList()
        }

        private fun searchInternal(name: String): List<Descriptions> {
            val result = arrayListOf<Descriptions>()

            val pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE)
            entries.forEach {
                val matcher = pattern.matcher(it.name_())
                if (matcher.find()) {
                    result.add(it)
                }
            }

            return result
        }
    }
}