package com.mirego.nwad.factories

import com.mirego.nwad.viewmodels.home.HomeViewModelController

class ViewModelControllerFactoryImpl(private val viewModelFactory: ViewModelFactory) :
    ViewModelControllerFactory {
    override fun createHome(): HomeViewModelController =
        HomeViewModelController(viewModelFactory)
}
