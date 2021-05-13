package com.mirego.nwad.viewmodels.home.impl

import com.mirego.trikot.kword.I18N
import com.mirego.trikot.streams.reactive.RefreshablePublisher
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.viewmodels.declarative.components.TextViewModel
import com.mirego.trikot.viewmodels.declarative.components.impl.TextViewModelImpl
import com.mirego.trikot.viewmodels.declarative.impl.ViewModelImpl

class HomeViewModelImpl(
) : HomeViewModel, ViewModelImpl(CancellableManager()) {
    override val labelViewModel = TextViewModelImpl(CancellableManager()).apply {
        text = "Hello world!"
    }
}
