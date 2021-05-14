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

external interface MomentsListComponentProp: RProps {
    var moments: ListViewModel<MomentViewModel>
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

val momentListComponent = functionalComponent<MomentsListComponentProp> { props ->
    val moments = useViewModelState(props.moments)

    for (moment in moments.elements) {
        br {  }
        img(src=(moment.image.image as ImageDescriptor.Remote).url){}
        br {  }
        +moment.title.text
    }
}

class App : RComponent<RProps, RState>() {
    val homeViewModel = Bootstrap.shared.viewModelFactory.homeViewModel(CancellableManager())

    override fun RBuilder.render() {
        +"Allo ${homeViewModel.labelViewModel.text}"

        child(momentListComponent) { attrs.moments = homeViewModel.moments }
    }
}
