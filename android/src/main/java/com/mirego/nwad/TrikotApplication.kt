package com.mirego.nwad

import android.app.Application
import com.mirego.trikot.http.HttpConfiguration

class TrikotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //AndroidKWord.setCurrentLanguageCode("en")
        //ImageViewModelResourceManager.provider = SampleImageResourceProvider()
        Environment.flavor = Environment.Flavor.valueOf(BuildConfig.BUILD_TYPE.toUpperCase())

        //HttpConfiguration.connectivityPublisher = AndroidConnectivityPublisher(this)
            //.distinctUntilChanged()
        //HttpConfiguration.httpRequestFactory = KtorHttpRequestFactory()
    }
}
