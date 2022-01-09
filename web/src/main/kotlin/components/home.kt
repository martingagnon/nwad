package components

import com.mirego.nwad.factories.Bootstrap
import com.mirego.trikot.streams.cancellable.CancellableManager
import react.Fragment
import react.PropsWithChildren
import react.dom.div
import react.functionComponent

@ExperimentalJsExport
val Home = functionComponent<PropsWithChildren> {
    val homeViewModel = Bootstrap.viewModelFactory.homeViewModel(CancellableManager())

    Fragment {
        div(classes = "container mx-auto px-4") {
            child(labelComponent) {
                attrs.viewModel = homeViewModel.labelViewModel
            }

            child(momentsComponent) {
                attrs.viewModel = homeViewModel.moments
            }
        }
    }
}
