package components

import com.mirego.nwad.factories.Bootstrap
import com.mirego.trikot.streams.cancellable.CancellableManager
import react.Fragment
import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.router.dom.hashRouter
import react.router.dom.route
import react.router.dom.switch

@ExperimentalJsExport
val Home = functionalComponent<RProps> {
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
