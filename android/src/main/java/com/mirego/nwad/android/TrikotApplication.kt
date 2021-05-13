package com.mirego.nwad.android

import android.app.Application
import com.mirego.nwad.BuildConfig
import com.mirego.nwad.Environment
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.HomeViewModelController
import com.mirego.trikot.viewmodels.declarative.controller.ViewModelControllerFactory
import com.mirego.trikot.viewmodels.declarative.controller.factory.ViewModelControllerFactoryProvidingApplication

class TrikotApplication : Application(), ViewModelControllerFactoryProvidingApplication {
    override fun onCreate() {
        super.onCreate()
        Environment.flavor = Environment.Flavor.valueOf(BuildConfig.BUILD_TYPE.toUpperCase())
    }

    override val viewModelControllerFactory: ViewModelControllerFactory
        get() = object: ViewModelControllerFactory {
            fun homeViewModelController(): HomeViewModelController {
                return HomeViewModelController()
            }
        }
}
