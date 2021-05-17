package com.mirego.nwad.factories

import com.mirego.nwad.domain.CreateMomentData
import com.mirego.nwad.domain.CreateMomentUseCase
import com.mirego.nwad.viewmodels.createmoment.CreateMomentViewModel
import com.mirego.nwad.viewmodels.createmoment.impl.CreateMomentViewModelImpl
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.impl.HomeViewModelImpl
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.promise.Promise

class ViewModelFactoryImpl(bootstrap: Bootstrap) : ViewModelFactory {
    override fun homeViewModel(cancellableManager: CancellableManager): HomeViewModel {
        return HomeViewModelImpl(cancellableManager)
    }

    override fun createMomentViewModel(cancellableManager: CancellableManager): CreateMomentViewModel {
        return CreateMomentViewModelImpl(
            cancellableManager,
            object : CreateMomentUseCase {
                override fun createNewMoment(createMomentData: CreateMomentData): Promise<Unit> {
                    return Promise.resolve(Unit)
                }
            }
        )
    }
}
