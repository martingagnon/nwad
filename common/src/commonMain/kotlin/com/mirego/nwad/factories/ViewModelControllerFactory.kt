package com.mirego.nwad.factories

import com.mirego.nwad.viewmodels.home.HomeViewModelController

interface ViewModelControllerFactory {
    fun createHome(): HomeViewModelController
}
