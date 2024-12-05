package io.github.gdrfgdrf.cutebedwars.abstracts.items

import org.bukkit.entity.Player

interface IItem {
    val properties: IItemProperties

    /**
     * 返回的 pair 的第一个为是否是新的 ICommonItem 第二个为 ICommonItem 实例
     */
    fun tryGive(player: Player, amount: Int = 1, slotIndex: Int = -1): Pair<Boolean, ICommonItem>
    fun give(player: Player, amount: Int = 1, slotIndex: Int = -1): ICommonItem
}