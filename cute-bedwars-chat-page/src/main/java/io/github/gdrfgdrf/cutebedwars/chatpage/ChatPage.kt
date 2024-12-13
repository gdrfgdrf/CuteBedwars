package io.github.gdrfgdrf.cutebedwars.chatpage

import com.google.common.collect.Lists
import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("chat_page", needArgument = true, instanceGetter = "get")
class ChatPage(
    private val pageRequest: PageRequest,
    private val lines: List<Line>
) : IChatPage {
    private val pages = arrayListOf<Page>()
    private var lineCountEveryPages = 5

    override var enableDefaultTopAndBottom: Boolean = true
    override var changeable = true
    override val size: Int = pages.size

    override fun send(index: Int) {
        if (pages.isEmpty()) {
            initPages()
        }
        if (pages.isEmpty()) {
            localizationScope(pageRequest.getSender()) {
                message(CommonLanguage.PAGE_LIST_EMPTY)
                    .send()
            }
            return
        }

        if (index >= pages.size) {
            localizationScope(pageRequest.getSender()) {
                message(CommonLanguage.PAGE_INDEX_OUT_OF_BOUNDS)
                    .format0(pages.size)
                    .send()
            }
            return
        }

        localizationScope(pageRequest.getSender()) {
            if (enableDefaultTopAndBottom) {
                message(CommonLanguage.PAGE_TOP)
                    .format0(index + 1, pages.size)
                    .send("")
            }

            val page = pages[index]
            page.send()

            if (enableDefaultTopAndBottom) {
                message(CommonLanguage.PAGE_BOTTOM)
                    .format0(index + 1, pages.size)
                    .send("")
            }
        }
    }

    override fun addPage(loader: (Int) -> List<ITranslationAgent>) {
        if (!changeable) {
            throw IllegalStateException("this change page is unchangeable");
        }
        val lines = loader(pages.size + 1).stream()
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
}