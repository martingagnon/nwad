package com.mirego.nwad.viewmodels.createmoment.impl

import com.mirego.nwad.domain.CreateMomentData
import com.mirego.nwad.domain.CreateMomentUseCase
import com.mirego.nwad.viewmodels.components.ImagePickerViewModel
import com.mirego.nwad.viewmodels.components.ImageViewModelContent
import com.mirego.nwad.viewmodels.components.TextViewModelContent
import com.mirego.nwad.viewmodels.createmoment.CreateMomentViewModel
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.streams.reactive.map
import com.mirego.trikot.streams.reactive.processors.safeCombine
import com.mirego.trikot.viewmodels.declarative.components.TextFieldViewModel
import com.mirego.trikot.viewmodels.declarative.components.impl.ButtonViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.ImageViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.TextFieldViewModelImpl
import com.mirego.trikot.viewmodels.declarative.components.impl.TextViewModelImpl
import com.mirego.trikot.viewmodels.declarative.impl.ViewModelImpl

class CreateMomentViewModelImpl(
    cancellableManager: CancellableManager,
    private val createMomentUseCase: CreateMomentUseCase
) : ViewModelImpl(cancellableManager), CreateMomentViewModel {
    private val isLoading = Publishers.behaviorSubject(false)
    private val imageData = Publishers.behaviorSubject<ByteArray>(ByteArray(0))

    override val loadingView = ViewModelImpl(cancellableManager).apply {
        bindHidden(isLoading.map { !it })
    }

    override val titleInput = TextFieldViewModelImpl(cancellableManager)

    override val cancelButton = ButtonViewModelImpl<ImageViewModelContent>(
        cancellableManager,
        object : ImageViewModelContent, ImageViewModelImpl(cancellableManager) {}
    ).apply {
        // setAction {
        //     // Todo navigate back
        // }
    }

    override fun onImageSelected(data: ByteArray) {
        imageData.value = data
    }

    override val submitButton = ButtonViewModelImpl<TextViewModelContent>(
        cancellableManager,
        object : TextViewModelContent, TextViewModelImpl(cancellableManager) {
            override var text = "Submit"
        }
    ).apply {
        bindEnabled(
            titleInput.publisherForProperty(TextFieldViewModel::text).safeCombine(imageData)
                .map { (text, imageData) -> !text.isNullOrEmpty() && imageData.size > 0 }
        )
    }

    init {
        submitButton.setAction {
            isLoading.value = true
            createMomentUseCase.createNewMoment(
                CreateMomentData(imageData.value!!, titleInput.text),
                cancellableManager
            ).finally {
                isLoading.value = false
            }
        }
    }
}
