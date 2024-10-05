package io.github.gdrfgdrf.cutebedwars.math.common

import java.util.concurrent.CopyOnWriteArrayList

class Arguments private constructor(vararg argument: Argument) {
    private val arguments = CopyOnWriteArrayList<Argument>()
    init {
        argument.forEach {
            arguments.add(it)
        }
    }

    fun length(): Int = arguments.size

    fun copy(): Arguments {
        return Arguments(*arguments.toTypedArray())
    }

    fun add(argument: Argument) {
        arguments.add(argument)
    }

    fun insert(index: Int, argument: Argument) {
        arguments.add(index, argument)
    }

    fun removeAt(index: Int) {
        arguments.removeAt(index)
    }

    operator fun get(index: Int): Argument {
        return arguments[index]
    }

    fun forEach(block: (Argument) -> Unit) {
        arguments.forEach(block)
    }

    fun arguments(): List<Argument> = arguments

    companion object {
        fun empty() = Arguments()

        fun of(vararg any: Any): Arguments {
            val array = arrayListOf<Argument>()
            any.forEach {
                array.add(Argument.of(it))
            }
            return Arguments(*array.toTypedArray())
        }

        fun from(vararg argument: Argument): Arguments {
            return Arguments(*argument)
        }
    }
}