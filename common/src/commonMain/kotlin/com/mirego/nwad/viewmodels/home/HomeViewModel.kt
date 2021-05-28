package com.mirego.nwad.viewmodels.home

import com.mirego.nwad.viewmodels.components.TextViewModelContent
import com.mirego.trikot.viewmodels.declarative.ViewModel
import com.mirego.trikot.viewmodels.declarative.components.ButtonViewModel
import com.mirego.trikot.viewmodels.declarative.components.ListViewModel
import com.mirego.trikot.viewmodels.declarative.components.TextViewModel

interface HomeViewModel : ViewModel {
    val loginButton: ButtonViewModel<TextViewModelContent>
    val logoutButton: ButtonViewModel<TextViewModelContent>
    val labelViewModel: TextViewModel
    val moments: ListViewModel<MomentViewModel>
}
