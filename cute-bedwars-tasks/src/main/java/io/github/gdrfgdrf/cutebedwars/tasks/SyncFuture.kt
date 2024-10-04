package io.github.gdrfgdrf.cutebedwars.tasks

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logError
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class SyncFuture<T> : Future<T> {
    private val latch = CountDownLatch(1)
    private var result: T? = null

    override fun cancel(mayInterruptIfRunning: Boolean): Boolean = false

    override fun isCancelled(): Boolean = false

    override fun isDone(): Boolean = result != null

    fun getSafety(): T? {
        runCatching {
            return get()
        }.onFailure {
            "Exception when getting result".logError(it)
        }
        return null
    }

    fun getSafety(timeout: Long, unit: TimeUnit): T? {
        runCatching {
            return get(timeout, unit)
        }.onFailure {
            "Exception when getting result".logError(it)
        }
        return null
    }

    override fun get(): T? {
        latch.await()
        return this.result
    }

    override fun get(timeout: Long, unit: TimeUnit): T? {
        if (latch.await(timeout, unit)) {
            return this.result
        }
        return null
    }

    fun result(
        result: T?
    ) {
        this.result = result
        latch.countDown()
    }
}