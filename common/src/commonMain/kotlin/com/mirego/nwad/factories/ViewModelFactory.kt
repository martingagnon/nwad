package com.mirego.nwad.factories

import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.trikot.streams.cancellable.CancellableManager

interface ViewModelFactory {
    fun homeViewModel(cancellableManager: CancellableManager): HomeViewModel
}