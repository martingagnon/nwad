package com.mirego.nwad.viewmodels.home.impl

import com.mirego.trikot.kword.I18N
import com.mirego.trikot.streams.reactive.RefreshablePublisher
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.trikot.foundation.FoundationConfiguration
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.TimerPublisher
import com.mirego.trikot.streams.reactive.map
import com.mirego.trikot.viewmodels.declarative.components.TextViewModel
import com.mirego.trikot.viewmodels.declarative.components.impl.TextViewModelImpl
import com.mirego.trikot.viewmodels.declarative.impl.ViewModelImpl
import kotlin.time.Duration
import kotlin.time.seconds

class HomeViewModelImpl(cancellableManager: CancellableManager) : HomeViewModel, ViewModelImpl(cancellableManager) {
    override val labelViewModel = TextViewModelImpl(cancellableManager).apply {
        bindText(TimerPublisher(1.seconds, FoundationConfiguration.timerFactory).map {
            "Hello world! $it"
        })
    }
}
