package com.pietrantuono.live

interface IntentConsumer<T : Intent> {

    fun acceptIntent(t: T)
}

interface Intent