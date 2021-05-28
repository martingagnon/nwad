package com.mirego.nwad.factories

import com.russhwolf.settings.Settings

object Bootstrap {
    val viewModelFactory: ViewModelFactory = ViewModelFactoryImpl(this)
    val viewModelControllerFactory: ViewModelControllerFactory = ViewModelControllerFactoryImpl(viewModelFactory)
}
