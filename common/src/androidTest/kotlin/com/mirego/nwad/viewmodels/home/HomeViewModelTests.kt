package com.mirego.nwad.viewmodels.home

import com.mirego.nwad.common.I18NMock
import com.mirego.nwad.domain.FetchQuoteUseCase
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.streams.cancellable.CancellableManager
import io.mockk.mockk
import org.junit.Test

class HomeViewModelTests {

    private val cancellableManager = CancellableManager()
    private val i18N: I18N = I18NMock()
    private val fetchQuoteUseCase = mockk<FetchQuoteUseCase>(relaxed = true)
    private val viewModelController = HomeViewModelController(
        fetchQuoteUseCase,
        i18N
    )
    private val viewModel = viewModelController.viewModel

    @Test
    fun whenTheScreenAppearThenWhoteIsAutomaticallyFetched() {
//        viewModel.quoteLabel.text.subscribe(cancellableManager, onNext = {})
//        verify(exactly = 1) { fetchQuoteUseCase.fetchQuote() }
    }

    @Test
    fun whenRefreshButtonIsTappedThenANewQuoteIsFetched() {
//        viewModel.quoteLabel.text.subscribe(cancellableManager, onNext = {})
//        viewModel.refreshButton.action.subscribe(cancellableManager, onNext = {
//            it.execute()
//        })
//        verify(exactly = 2) { fetchQuoteUseCase.fetchQuote() }
    }
}
