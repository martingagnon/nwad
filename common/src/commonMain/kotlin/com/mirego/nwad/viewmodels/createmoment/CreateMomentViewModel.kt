package com.mirego.nwad.viewmodels.createmoment

import com.mirego.nwad.viewmodels.components.ImageViewModelContent
import com.mirego.nwad.viewmodels.components.TextViewModelContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDTextFieldViewModel

interface CreateMomentViewModel {
    val loadingView: VMDViewModel
    val submitButton: VMDButtonViewModel<TextViewModelContent>
    val cancelButton: VMDButtonViewModel<ImageViewModelContent>
    val titleInput: VMDTextFieldViewModel
    fun setImageData(jpegImageData: ByteArray)
}
