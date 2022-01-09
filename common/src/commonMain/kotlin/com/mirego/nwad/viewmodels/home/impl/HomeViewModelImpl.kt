package com.mirego.nwad.viewmodels.home.impl

import com.mirego.nwad.domain.IsLoggedUseCase
import com.mirego.nwad.domain.LogoutUseCase
import com.mirego.nwad.domain.impl.FetchMomentsUseCaseImpl
import com.mirego.nwad.repositories.impl.MomentsRepositoryImpl
import com.mirego.nwad.viewmodels.components.TextViewModelContent
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.foundation.FoundationConfiguration
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.streams.reactive.map
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDButtonViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDListViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDTextViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import kotlin.time.seconds

class HomeViewModelImpl(
    cancellableManager: CancellableManager,
    isLoggedUseCase: IsLoggedUseCase,
    logoutUseCase: LogoutUseCase,
    i18N: I18N
) : HomeViewModel, VMDViewModelImpl(cancellableManager) {
    private val publisher = Publishers.behaviorSubject(0)

    override val labelViewModel = VMDTextViewModelImpl(cancellableManager).apply {
        text = "initial"
        bindText(publisher.map { "Hello world! $it" })
    }

    override val moments = VMDListViewModelImpl<MomentViewModel>(cancellableManager).apply {
        bindElements(
            FetchMomentsUseCaseImpl(MomentsRepositoryImpl()).fetchMoments().map { moments ->
                moments.map {
                    MomentViewModelImpl(cancellableManager, it)
                }
            }
        )
    }

    override val loginButton = VMDButtonViewModelImpl<TextViewModelContent>(
        cancellableManager,
        object : TextViewModelContent, VMDTextViewModelImpl(cancellableManager) {
            override var text = "Login"
        }
    ).apply {
        bindHidden(isLoggedUseCase.isLogged())
    }

    override val logoutButton = VMDButtonViewModelImpl<TextViewModelContent>(
        cancellableManager,
        object : TextViewModelContent, VMDTextViewModelImpl(cancellableManager) {
            override var text = "Logout"
        }
    ).apply {
        bindHidden(isLoggedUseCase.isLogged().map { !it })
        setAction {
            logoutUseCase.logout()
        }
    }

    init {
        FoundationConfiguration.timerFactory.repeatable(1.seconds) { publisher.value = publisher.value!! + 1 }.also {
            cancellableManager.add { it.cancel() }
        }
    }
}
