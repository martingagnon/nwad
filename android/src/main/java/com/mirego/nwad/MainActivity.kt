package com.mirego.nwad

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContentProviderCompat.requireContext
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.HomeViewModelController
import com.mirego.trikot.viewmodels.declarative.controller.NavigationDelegate
import com.mirego.trikot.viewmodels.declarative.controller.ViewModelActivity

class MainActivity : ViewModelActivity<HomeViewModelController, HomeViewModel, NavigationDelegate>() {
    override val viewModelController: HomeViewModelController by lazy {
        getViewModelController(
            HomeViewModelController::class
        )
    }

    @Composable
    fun composableContent(viewModel: HomeViewModel) {
        Text(text = "Hellow Android")
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                composableContent(viewModel)
            }
        }
    }
}
