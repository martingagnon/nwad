package com.mirego.nwad.factories

import com.mirego.trikot.kword.I18N
import com.mirego.nwad.viewmodels.home.HomeViewModelController

class ViewModelControllerFactoryImpl(private val i18N: I18N) :
    ViewModelControllerFactory {
    override fun createHome(): HomeViewModelController =
        HomeViewModelController()
}
