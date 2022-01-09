package com.mirego.nwad.viewmodels.home

import com.mirego.nwad.factories.ViewModelFactory
import com.mirego.trikot.viewmodels.declarative.controller.VMDNavigationDelegate
import com.mirego.trikot.viewmodels.declarative.controller.VMDViewModelController

class HomeViewModelController(viewModelFactory: ViewModelFactory) :
    VMDViewModelController<HomeViewModel, VMDNavigationDelegate>() {
    override val viewModel: HomeViewModel = viewModelFactory.homeViewModel(cancellableManager)
}
