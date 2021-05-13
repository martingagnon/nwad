package com.mirego.nwad.viewmodels.home.impl

import com.mirego.nwad.models.Moment
import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.viewmodels.declarative.components.impl.ImageViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.TextViewModelImpl
import com.mirego.trikot.viewmodels.declarative.impl.ViewModelImpl
import com.mirego.trikot.viewmodels.declarative.properties.ImageDescriptor

class MomentViewModelImpl(cancellableManager: CancellableManager, moment: Moment): MomentViewModel, ViewModelImpl(cancellableManager) {
    override val title = TextViewModelImpl(cancellableManager).apply {
        text = moment.description
    }
    override val image = ImageViewModelImpl(cancellableManager).apply {
        image = ImageDescriptor.Remote(moment.media.thumb)
    }
    override val identifier = moment.id
}