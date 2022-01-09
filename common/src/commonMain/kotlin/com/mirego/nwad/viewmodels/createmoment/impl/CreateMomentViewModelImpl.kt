package com.mirego.nwad.viewmodels.createmoment.impl

import com.mirego.nwad.domain.CreateMomentData
import com.mirego.nwad.domain.CreateMomentUseCase
import com.mirego.nwad.viewmodels.components.ImageViewModelContent
import com.mirego.nwad.viewmodels.components.TextViewModelContent
import com.mirego.nwad.viewmodels.createmoment.CreateMomentViewModel
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.streams.reactive.map
import com.mirego.trikot.streams.reactive.processors.safeCombine
import com.mirego.trikot.viewmodels.declarative.components.VMDTextFieldViewModel
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDButtonViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDImageViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDTextFieldViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDTextViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl

class CreateMomentViewModelImpl(
    cancellableManager: CancellableManager,
    private val createMomentUseCase: CreateMomentUseCase
) : VMDViewModelImpl(cancellableManager), CreateMomentViewModel {
    private val isLoading = Publishers.behaviorSubject(false)
    private val imageData = Publishers.behaviorSubject<ByteArray>(ByteArray(0))

    private fun submitMoment() {
        isLoading.value = true
        createMomentUseCase.createNewMoment(
            CreateMomentData(imageData.value!!, titleInput.text)
        ).finally {
            isLoading.value = false
        }
    }

    override val loadingView = VMDViewModelImpl(cancellableManager).apply {
        bindHidden(isLoading.map { !it })
    }

    override val titleInput = VMDTextFieldViewModelImpl(cancellableManager)

    override val cancelButton = VMDButtonViewModelImpl<ImageViewModelContent>(
        cancellableManager,
        object : ImageViewModelContent, VMDImageViewModelImpl(cancellableManager) {}
    ).apply {
        setAction {
            // Todo navigate back
        }
    }

    override val submitButton = VMDButtonViewModelImpl<TextViewModelContent>(
        cancellableManager,
        object : TextViewModelContent, VMDTextViewModelImpl(cancellableManager) {
            override var text = "Submit"
        }
    ).apply {
        bindEnabled(
            titleInput.publisherForProperty(VMDTextFieldViewModel::text).safeCombine(imageData)
                .map { (text, imageData) -> !text.isNullOrEmpty() && imageData.size > 0 }
        )
        setAction {
            submitMoment()
        }
    }

    override fun setImageData(jpegImageData: ByteArray) {
        imageData.value = jpegImageData
    }
}
