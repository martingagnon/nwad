package com.mirego.nwad.android

import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.HomeViewModelController
import com.mirego.trikot.viewmodels.declarative.controller.NavigationDelegate
import com.mirego.trikot.viewmodels.declarative.controller.ViewModelActivity

class MainActivity :
    ViewModelActivity<HomeViewModelController, HomeViewModel, NavigationDelegate>() {
    override val viewModelController: HomeViewModelController by lazy {
        getViewModelController(
            HomeViewModelController::class
        )
    }

//    @Composable
//    fun composableContent(viewModel: HomeViewModel) {
//        Text(text = "Hellow Android")
//    }

//    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        return super.on
//        return ComposeView(context).apply {
//            setContent {
//                composableContent(viewModel)
//            }
//        }
//    }
}
