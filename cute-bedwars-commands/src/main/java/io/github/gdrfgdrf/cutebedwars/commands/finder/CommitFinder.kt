package io.github.gdrfgdrf.cutebedwars.commands.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractAreaEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractGameEditor
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit

object CommitFinder {
    fun linkFind(editor: AbstractEditor<*>, commitId: String): MutableList<Commit>? {
        if (editor is AbstractAreaEditor) {
            val commitStorage = editor.t.manager.commitStorage ?: return null
            val commitsList = commitStorage.get()?.commitsList ?: return null

            val targetCommit = commitsList.stream()
                .filter {
                    it.id == commitId
                }
                .findAny()
                .orElse(null) ?: return null

            val targetIndex = commitsList.indexOf(targetCommit)
            val result = arrayListOf<Commit>()
            result.add(targetCommit)

            commitsList.forEachIndexed { index, commit ->
                if (index > targetIndex) {
                    result.add(commit)
                }
            }

            return result
        }
        if (editor is AbstractGameEditor) {
            val gameContext = editor.t

            val commitsList =
                gameContext.commitStorage.get()?.getMapOrDefault(gameContext.game.id.toString(), null)?.commitsList
                    ?: return null

            val targetCommit = commitsList.stream()
                .filter {
                    it.id == commitId
                }
                .findAny()
                .orElse(null) ?: return null

            val targetIndex = commitsList.indexOf(targetCommit)
            val result = arrayListOf<Commit>()
            result.add(targetCommit)

            commitsList.forEachIndexed { index, commit ->
                if (index > targetIndex) {
                    result.add(commit)
                }
            }

            return result
        }
        return null
    }

    fun find(editor: AbstractEditor<*>, commitId: String): Commit? {
        if (editor is AbstractAreaEditor) {
            return editor.t.manager.commitStorage?.get()?.commitsList?.stream()
                ?.filter {
                    it.id == commitId
                }
                ?.findAny()
                ?.orElse(null)
        }
        if (editor is AbstractGameEditor) {
            val gameContext = editor.t

            val commitsList =
                gameContext.commitStorage.get()?.getMapOrDefault(gameContext.game.id.toString(), null)?.commitsList
            return commitsList?.stream()
                ?.filter {
                    it.id == commitId
                }
                ?.findAny()
                ?.orElse(null)
        }

        return null
    }
}