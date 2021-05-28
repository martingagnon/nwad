package com.mirego.nwad.factories

object Bootstrap {
    val viewModelFactory: ViewModelFactory = ViewModelFactoryImpl(this)
    val viewModelControllerFactory: ViewModelControllerFactory = ViewModelControllerFactoryImpl(viewModelFactory)
}
