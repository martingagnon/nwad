import com.mirego.nwad.factories.Bootstrap
import com.mirego.trikot.streams.cancellable.CancellableManager
import kotlinx.browser.document
import react.dom.div
import react.dom.render

@ExperimentalJsExport
fun main() {
    kotlinext.js.require("bootstrap/dist/css/bootstrap.min.css")

    render(document.getElementById("root")) {
        div {
            +"Allo${Bootstrap.shared.viewModelFactory.homeViewModel(CancellableManager()).labelViewModel.text}"
        }
    }
}
