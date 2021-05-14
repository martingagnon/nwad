import com.mirego.nwad.factories.Bootstrap
import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.http.web.WebHttpRequest
import com.mirego.trikot.http.web.WebHttpRequestFactory
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.subscribe
import com.mirego.trikot.viewmodels.declarative.properties.ImageDescriptor
import kotlinx.browser.document
import react.*
import react.dom.br
import react.dom.div
import react.dom.img
import react.dom.render

@ExperimentalJsExport
fun main() {
    kotlinext.js.require("bootstrap/dist/css/bootstrap.min.css")
    HttpConfiguration.httpRequestFactory = WebHttpRequestFactory()

    render(document.getElementById("root")) {
        div {
            child(App::class) {}
        }
    }
}

external interface MomentsState: RState {
    var moments: List<MomentViewModel>?
}

class App : RComponent<RProps, MomentsState>() {
    val homeViewModel = Bootstrap.shared.viewModelFactory.homeViewModel(CancellableManager())

    override fun RBuilder.render() {
        +"${homeViewModel.labelViewModel.text}"
        for (moment in state.moments ?: emptyList()) {
            br {  }
            img(src=(moment.image.image as ImageDescriptor.Remote).url){}
            br {  }
            +"${moment.title.text}"
        }

        homeViewModel.moments.propertyDidChange.subscribe(CancellableManager()) {
            setState {
                moments = homeViewModel.moments.elements
            }
        }
    }
}
