package com.mirego.nwad.factories

import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.impl.HomeViewModelImpl
import com.mirego.trikot.streams.cancellable.CancellableManager

class ViewModelFactoryImpl(bootstrap: Bootstrap): ViewModelFactory  {
    override fun homeViewModel(cancellableManager: CancellableManager): HomeViewModel {
        return HomeViewModelImpl(cancellableManager)
    }
}