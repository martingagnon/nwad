package components

import com.mirego.nwad.factories.Bootstrap
import com.mirego.trikot.streams.cancellable.CancellableManager
import kotlinext.js.jso
import react.FC
import react.Fragment
import react.PropsWithChildren
import react.dom.html.ReactHTML.div

@ExperimentalJsExport
val Home = FC<PropsWithChildren> {
    val homeViewModel = Bootstrap.viewModelFactory.homeViewModel(CancellableManager())

    Fragment {
        div {
            className = "container mx-auto px-4"

            child(labelComponent, jso {
                viewModel = homeViewModel.labelViewModel
            })

            child(momentsComponent, jso {
                viewModel = homeViewModel.moments
            })
        }
    }
}
