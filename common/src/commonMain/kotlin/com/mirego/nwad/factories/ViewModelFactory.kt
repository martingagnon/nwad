package com.mirego.nwad.factories

import com.mirego.nwad.viewmodels.createmoment.CreateMomentViewModel
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.login.LoginViewModel
import com.mirego.trikot.streams.cancellable.CancellableManager

interface ViewModelFactory {
    fun homeViewModel(cancellableManager: CancellableManager): HomeViewModel
    fun createMomentViewModel(cancellableManager: CancellableManager): CreateMomentViewModel
    fun loginViewModel(): LoginViewModel
}
