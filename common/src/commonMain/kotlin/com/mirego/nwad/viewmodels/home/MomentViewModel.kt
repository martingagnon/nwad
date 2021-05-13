package com.mirego.nwad.viewmodels.home

import com.mirego.trikot.viewmodels.declarative.ViewModel
import com.mirego.trikot.viewmodels.declarative.components.ImageViewModel
import com.mirego.trikot.viewmodels.declarative.components.TextViewModel
import com.mirego.trikot.viewmodels.declarative.properties.IdentifiableContent

interface MomentViewModel: ViewModel, IdentifiableContent {
    val title: TextViewModel
    val image: ImageViewModel
}