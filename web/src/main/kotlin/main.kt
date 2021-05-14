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
import react.*
import react.dom.br
import react.dom.div
import react.dom.img
import react.dom.render
import kotlin.reflect.KMutableProperty1

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

inline fun <T: ViewModel> useViewModelState(viewModel: T): T {
    val (delegate, setDelegate) = useState(Pair(viewModel, 0))
    val cancellableManager = CancellableManager()
    useEffectWithCleanup {
        viewModel.propertyDidChange.subscribe(cancellableManager) {
            setDelegate(Pair(viewModel, delegate.second + 1))
        }

        return@useEffectWithCleanup { cancellableManager.cancel() }
    }
    return delegate.first
}

external interface ViewModelComponentProp<T: ViewModel>: RProps {
    var viewModel: T
}

val momentListComponent = functionalComponent<ViewModelComponentProp<ListViewModel<MomentViewModel>>> { props ->
    val moments = useViewModelState(props.viewModel)

    for (moment in moments.elements) {
        br {  }
        img(src=(moment.image.image as ImageDescriptor.Remote).url){}
        br {  }
        +moment.title.text
    }
}

val labelComponent = functionalComponent<ViewModelComponentProp<TextViewModel>> { props ->
    val label = useViewModelState(props.viewModel)
    +label.text
}

class App : RComponent<RProps, RState>() {
    val homeViewModel = Bootstrap.shared.viewModelFactory.homeViewModel(CancellableManager())

    override fun RBuilder.render() {
        child(labelComponent) { attrs.viewModel = homeViewModel.labelViewModel }
        child(momentListComponent) { attrs.viewModel = homeViewModel.moments }
    }
}
