package com.mirego.nwad.viewmodels.home.impl

import com.mirego.nwad.models.Moment
import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDImageViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDTextViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageDescriptor

class MomentViewModelImpl(
    cancellableManager: CancellableManager,
    moment: Moment
) : MomentViewModel, VMDViewModelImpl(cancellableManager) {
    override val title = VMDTextViewModelImpl(cancellableManager).apply {
        text = moment.description
    }
    override val image = VMDImageViewModelImpl(cancellableManager).apply {
        image = VMDImageDescriptor.Remote(moment.media.thumb)
    }
    override val identifier = moment.id
}
