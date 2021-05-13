package com.mirego.nwad.factories

import com.mirego.trikot.kword.KWord

class Bootstrap {
    val viewModelFactory: ViewModelFactory = ViewModelFactoryImpl(this)
    val viewModelControllerFactory: ViewModelControllerFactory = ViewModelControllerFactoryImpl(viewModelFactory)

    companion object {
        val shared = Bootstrap()
    }
}
