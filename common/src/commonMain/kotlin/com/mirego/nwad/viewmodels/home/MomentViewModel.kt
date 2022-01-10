package com.mirego.nwad.viewmodels.home

import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDImageViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDTextViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDIdentifiableContent

interface MomentViewModel : VMDViewModel, VMDIdentifiableContent {
    val title: VMDTextViewModel
    val image: VMDImageViewModel
}
