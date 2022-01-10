package com.mirego.nwad.android

import android.app.Application
import com.mirego.nwad.BuildConfig
import com.mirego.nwad.Environment
import com.mirego.nwad.android.resources.ImageProvider
import com.mirego.nwad.factories.Bootstrap
import com.mirego.nwad.viewmodels.home.HomeViewModelController
import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.http.android.requestFactory.KtorHttpRequestFactory
import com.mirego.trikot.viewmodels.declarative.configuration.TrikotViewModelDeclarative
import com.mirego.trikot.viewmodels.declarative.controller.VMDViewModelControllerFactory
import com.mirego.trikot.viewmodels.declarative.controller.factory.ViewModelControllerFactoryProvidingApplication

@kotlin.time.ExperimentalTime
class TrikotApplication : Application(), ViewModelControllerFactoryProvidingApplication {
    override fun onCreate() {
        super.onCreate()
        Environment.flavor = Environment.Flavor.valueOf(BuildConfig.BUILD_TYPE.toUpperCase())
        HttpConfiguration.httpRequestFactory = KtorHttpRequestFactory()
        TrikotViewModelDeclarative.initialize(ImageProvider())
    }

    override val viewModelControllerFactory: VMDViewModelControllerFactory
        get() = object : VMDViewModelControllerFactory {
            fun homeViewModelController(): HomeViewModelController {
                return HomeViewModelController(Bootstrap.viewModelFactory)
            }
        }
}
