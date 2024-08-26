package io.github.gdrfgdrf.cutebedwars.chatpage

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.google.common.collect.Lists
import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit

@ServiceImpl("chat_page", needArgument = true, instanceGetter = "get")
class ChatPage(
    val pageRequest: PageRequest,
    private val lines: List<Line>,
) : IChatPage {
    private val pages = arrayListOf<Page>()
        get() {
            if (field.isEmpty()) {
                initPages()
            }
            return field
        }
    var lineCountEveryPages = 5

    override fun send(index: Int) {
        if (index >= pages.size) {
            localizationScope(pageRequest.getSender()) {
                message(CommonLanguage.PAGE_INDEX_OUT_OF_BOUNDS)
                    .format(pages.size)
                    .send()
            }
            return
        }

        localizationScope(pageRequest.getSender()) {
            message(CommonLanguage.PAGE_TOP)
                .format(index + 1, pages.size)
                .send("")

            val page = pages[index]
            page.send()

            message(CommonLanguage.PAGE_BOTTOM)
                .format(index + 1, pages.size)
                .send("")
        }
    }

    override fun size(): Int {
        return pages.size
    }

    override fun addPage(loader: () -> List<ILocalizationMessage>) {
        val lines = loader().stream()
            .map {
                return@map Line(it)
            }
            .toList()
        pages.add(Page(lines))
    }

    override fun lineCountEveryPages(): Int = lineCountEveryPages

    override fun lineCountEveryPages(lineCount: Int) {
        this.lineCountEveryPages = lineCount
    }

    private fun initPages() {
        val partition = Lists.partition(lines, lineCountEveryPages)
        partition.forEach {
            pages.add(Page(it))
        }
    }

    companion object {
        private val cache: LoadingCache<PageRequest, ChatPage>

        init {
            val loader = object : CacheLoader<PageRequest, ChatPage>() {
                override fun load(pageRequest: PageRequest): ChatPage {
                    return Companion.load(pageRequest)
                }
            }
            cache = CacheBuilder.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build(loader)
        }

        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun get(argumentSet: ArgumentSet): ChatPage {
            return get(
                argumentSet.args[0] as CommandSender,
                argumentSet.args[1] as IPageRequestTypes,
                argumentSet.args[2] as String,
                argumentSet.args[3] as () -> List<ILocalizationMessage>
            )
        }

        fun get(
            sender: CommandSender,
            pageRequests: IPageRequestTypes,
            flagContent: String,
            loader: () -> List<ILocalizationMessage>,
        ): ChatPage {
            val uuid = if (sender is Player) {
                sender.uniqueId.toString()
            } else {
                "not_a_player"
            }
            val request = PageRequest(uuid, pageRequests, flagContent, loader)
            if (!pageRequests.cache()) {
                return load(request)
            }

            return cache.get(request)
        }

        private fun load(pageRequest: PageRequest): ChatPage {
            val loader = pageRequest.loader
            val lines = loader().stream()
                .map(::Line)
                .toList()

            return ChatPage(pageRequest, lines)
        }
    }
}