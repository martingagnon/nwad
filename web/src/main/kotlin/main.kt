import com.mirego.nwad.factories.Bootstrap
import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.http.web.WebHttpRequestFactory
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.subscribe
import com.mirego.trikot.viewmodels.declarative.ViewModel
import com.mirego.trikot.viewmodels.declarative.components.ListViewModel
import com.mirego.trikot.viewmodels.declarative.components.TextViewModel
import com.mirego.trikot.viewmodels.declarative.properties.ImageDescriptor
import kotlinx.browser.document
import movetotrikot.ViewModelComponentProp
import movetotrikot.useViewModelState
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

val momentListComponent = functionalComponent<ViewModelComponentProp<ListViewModel<MomentViewModel>>> { props ->
    val moments = useViewModelState(props)

    for (moment in moments.elements) {
        br {  }
        img(src=(moment.image.image as ImageDescriptor.Remote).url){}
        br {  }
        +moment.title.text
    }
}

val labelComponent = functionalComponent<ViewModelComponentProp<TextViewModel>> { props ->
    val label = useViewModelState(props)
    +label.text
}

class App : RComponent<RProps, RState>() {
    val homeViewModel = Bootstrap.shared.viewModelFactory.homeViewModel(CancellableManager())

    override fun RBuilder.render() {
        child(labelComponent) { attrs.viewModel = homeViewModel.labelViewModel }
        child(momentListComponent) { attrs.viewModel = homeViewModel.moments }
    }
}
