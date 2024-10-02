package io.github.gdrfgdrf.cutebedwars.abstracts.editing.change

abstract class AbstractChange<T>(val name: String) {
    abstract fun validate(): Boolean
    abstract fun apply(t: T)
    abstract fun makeUndo(): AbstractChange<T>
    abstract fun args(): Array<Any?>

    @Suppress("UNCHECKED_CAST")
    fun annotationName(): String {
        val changeAnnotationClass = Class.forName("io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.Change") as Class<Annotation>

        val change = this::class.java.getAnnotation(changeAnnotationClass)
        val nameField = changeAnnotationClass.getDeclaredMethod("name")
        val changeName = nameField.invoke(change)

        return changeName as String
    }

}