package com.mirego.nwad.android

import android.app.Application
import com.mirego.nwad.BuildConfig
import com.mirego.nwad.Environment

class TrikotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Environment.flavor = Environment.Flavor.valueOf(BuildConfig.BUILD_TYPE.toUpperCase())
    }
}
