package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticles
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelection
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.asyncTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.sleepSafely
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Dev : AbstractSubCommand(
    command = ICommands.valueOf("DEV")
) {
    override fun syntax(): LanguageString? = null
    override fun description(): LanguageString? = null

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        val selection = ISelection.new(10.0, 10.0, 10.0, 20.0, 20.0, 20.0)

        val overworld = Bukkit.getServer().getWorld("world")
        val stopSignal = selection.spawnParticle(Particle.REDSTONE, overworld, 1000)

        asyncTask {
            sleepSafely(10000)
            stopSignal.stop()
        }
    }
}