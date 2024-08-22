package io.github.gdrfgdrf.cutebedwars.utils.thread

import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

@Suppress("REMOVAL")
object NamedThreadFactory : ThreadFactory {
    private val poolCount = AtomicInteger()
    private val count = AtomicInteger()
    private var group: ThreadGroup? = null

    init {
        poolCount.incrementAndGet()
        val securityManager = System.getSecurityManager()
        group = if (securityManager != null) securityManager.threadGroup else Thread.currentThread().threadGroup
    }

    override fun newThread(r: Runnable): Thread {
        val result = Thread(group, r)
        result.name = "Pool-" + poolCount.incrementAndGet() + " Thread-" + count.incrementAndGet()
        return result
    }
}