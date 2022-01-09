package com.mirego.nwad.viewmodels.home

import com.mirego.nwad.viewmodels.components.TextViewModelContent
import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDListViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDTextViewModel
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface HomeViewModel : VMDViewModel {
    val loginButton: VMDButtonViewModel<TextViewModelContent>
    val logoutButton: VMDButtonViewModel<TextViewModelContent>
    val labelViewModel: VMDTextViewModel
    val moments: VMDListViewModel<MomentViewModel>
}
