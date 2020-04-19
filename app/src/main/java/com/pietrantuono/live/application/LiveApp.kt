package com.pietrantuono.live.application

import android.app.Application

class LiveApp : Application() {
    val appComponent by lazy { DaggerAppComponent.create() }
}