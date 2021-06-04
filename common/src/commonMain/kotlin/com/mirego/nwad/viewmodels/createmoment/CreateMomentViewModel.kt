package com.mirego.nwad.viewmodels.createmoment

import com.mirego.nwad.viewmodels.components.ImagePickerViewModel
import com.mirego.nwad.viewmodels.components.ImageViewModelContent
import com.mirego.nwad.viewmodels.components.TextViewModelContent
import com.mirego.trikot.viewmodels.declarative.ViewModel
import com.mirego.trikot.viewmodels.declarative.components.ButtonViewModel
import com.mirego.trikot.viewmodels.declarative.components.TextFieldViewModel

interface CreateMomentViewModel: ImagePickerViewModel {
    val loadingView: ViewModel
    val submitButton: ButtonViewModel<TextViewModelContent>
    val cancelButton: ButtonViewModel<ImageViewModelContent>
    val titleInput: TextFieldViewModel
}
