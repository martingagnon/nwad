package com.mirego.nwad.factories

class Bootstrap {
    val viewModelFactory: ViewModelFactory = ViewModelFactoryImpl(this)
    val viewModelControllerFactory: ViewModelControllerFactory = ViewModelControllerFactoryImpl(viewModelFactory)

    companion object {
        val shared = Bootstrap()
    }
}
