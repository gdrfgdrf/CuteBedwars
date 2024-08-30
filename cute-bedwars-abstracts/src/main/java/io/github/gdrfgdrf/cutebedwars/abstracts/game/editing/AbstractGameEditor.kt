package io.github.gdrfgdrf.cutebedwars.abstracts.game.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext

abstract class AbstractGameEditor(uuid: String, gameContext: IGameContext) : AbstractEditor<IGameContext>(uuid, gameContext) {
}