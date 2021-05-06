package com.mirego.nwad.viewmodels.home

import com.mirego.trikot.kword.I18N
import com.mirego.trikot.streams.reactive.RefreshablePublisher
import com.mirego.nwad.domain.FetchQuoteUseCase
import com.mirego.nwad.viewmodels.base.BaseNavigationDelegate
import com.mirego.nwad.viewmodels.base.BaseViewModelController
import com.mirego.nwad.viewmodels.home.impl.HomeViewModelImpl

class HomeViewModelController(
    fetchQuoteUseCase: FetchQuoteUseCase,
    i18N: I18N
) : BaseViewModelController<BaseNavigationDelegate, HomeViewModel>() {
    private val refreshableQuotePublisher = RefreshablePublisher({ _, _ ->
        fetchQuoteUseCase.fetchQuote()
    })

    override val viewModel: HomeViewModel = HomeViewModelImpl(refreshableQuotePublisher, i18N)
}
