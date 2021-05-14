package com.mirego.nwad.viewmodels.home

import com.mirego.trikot.viewmodels.declarative.ViewModel
import com.mirego.trikot.viewmodels.declarative.components.ListViewModel
import com.mirego.trikot.viewmodels.declarative.components.TextViewModel

interface HomeViewModel : ViewModel {
    val labelViewModel: TextViewModel
    val moments: ListViewModel<MomentViewModel>
}
