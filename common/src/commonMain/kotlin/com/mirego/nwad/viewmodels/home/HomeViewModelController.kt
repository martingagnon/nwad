package com.mirego.nwad.viewmodels.home

import com.mirego.nwad.viewmodels.home.impl.HomeViewModelImpl
import com.mirego.trikot.viewmodels.declarative.controller.NavigationDelegate
import com.mirego.trikot.viewmodels.declarative.controller.ViewModelController

class HomeViewModelController(
) : ViewModelController<HomeViewModel, NavigationDelegate>() {
    override val viewModel: HomeViewModel = HomeViewModelImpl(cancellableManager)
}
