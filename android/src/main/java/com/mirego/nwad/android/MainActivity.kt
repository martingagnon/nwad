package com.mirego.nwad.android

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.HomeViewModelController
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.RemoteImage
import com.mirego.trikot.viewmodels.declarative.controller.VMDNavigationDelegate
import com.mirego.trikot.viewmodels.declarative.controller.ViewModelActivity
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageDescriptor

class MainActivity :
    ViewModelActivity<HomeViewModelController, HomeViewModel, VMDNavigationDelegate>() {
    override val viewModelController: HomeViewModelController by lazy {
        getViewModelController(
            HomeViewModelController::class
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            view(viewModelController.viewModel)
        }
    }

    @Composable
    fun view(homeViewModel: HomeViewModel) {

        val obsText =
            homeViewModel.labelViewModel.observeAsState(property = homeViewModel.labelViewModel::text)
        val moments =
            homeViewModel.moments.observeAsState(property = homeViewModel.moments::elements)

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(obsText.value, color = Color.Blue)
            moments.value.forEach { moment ->
                RemoteImage(imageUrl = (moment.image.image as VMDImageDescriptor.Remote).url)
                Text(moment.title.text, color = Color.Black)
            }
        }
    }
}
