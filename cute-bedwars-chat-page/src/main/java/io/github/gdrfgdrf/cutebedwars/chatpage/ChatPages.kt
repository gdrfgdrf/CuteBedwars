package io.github.gdrfgdrf.cutebedwars.chatpage

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPages
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logDebug
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.uuid
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit

@ServiceImpl("chat_pages")
object ChatPages : IChatPages {
    private var cache: LoadingCache<PageRequest, IChatPage>? = null
    private var initialized = false

    private fun check() {
        if (!initialized) {
            throw IllegalStateException("chat pages is not initialized")
        }
    }

    override fun initialize() {
        "Initializing chat pages".logInfo()

        val loader = object : CacheLoader<PageRequest, IChatPage>() {
            override fun load(pageRequest: PageRequest): IChatPage {
                "Creating new chat page in the cache, uuid: ${pageRequest.uuid}, type: ${pageRequest.type}, flagContent: ${pageRequest.flagContent}".logDebug()
                return this@ChatPages.load(pageRequest)
            }
        }
        cache = if (IConfig.get<String>("ChatPageCacheBuilderSpecification").isBlank()) {
            "Creating chat page's caching with the default specification".logInfo()
            CacheBuilder.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build(loader)
        } else {
            "Creating chat page's caching with the custom specification".logInfo()
            CacheBuilder.from(IConfig.get<String>("ChatPageCacheBuilderSpecification"))
                .build(loader)
        }

        initialized = true
    }

    override fun disable() {
        cache = null
        initialized = false
    }

    override fun cache(
        sender: CommandSender,
        pageRequestTypes: IPageRequestTypes,
        flagContent: String,
        loader: () -> List<ITranslationAgent>,
    ): IChatPage {
        check()
        val uuid = sender.uuid()
        val request = PageRequest(uuid, pageRequestTypes, flagContent, loader)
        if (!pageRequestTypes.cache()) {
            "The caching for $pageRequestTypes is disabled, creating new chat page".logDebug()
            return load(request)
        }

        return cache!!.get(request)
    }

    private fun load(pageRequest: PageRequest): IChatPage {
        check()
        val loader = pageRequest.loader
        val lines = loader().stream()
            .map(::Line)
            .toList()

        return ChatPage(pageRequest, lines)
    }
}