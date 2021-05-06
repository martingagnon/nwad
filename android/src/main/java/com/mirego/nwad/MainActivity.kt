package com.mirego.nwad

import com.mirego.nwad.common.BaseViewModelActivity
import com.mirego.nwad.databinding.ActivityMainBinding
import com.mirego.nwad.viewmodels.base.BaseNavigationDelegate
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.HomeViewModelController

class MainActivity : BaseViewModelActivity<BaseNavigationDelegate, HomeViewModel>() {
    override val viewModelController: HomeViewModelController by lazy {
        getViewModelController(
            HomeViewModelController::class
        )
    }

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }
}
