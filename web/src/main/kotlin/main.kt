import com.mirego.nwad.factories.Bootstrap
import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.http.web.WebHttpRequest
import com.mirego.trikot.http.web.WebHttpRequestFactory
import com.mirego.trikot.streams.cancellable.CancellableManager
import kotlinx.browser.document
import react.dom.div
import react.dom.render

@ExperimentalJsExport
fun main() {
    kotlinext.js.require("bootstrap/dist/css/bootstrap.min.css")
    HttpConfiguration.httpRequestFactory = WebHttpRequestFactory()

    render(document.getElementById("root")) {
        div {
            +"Allo${Bootstrap.shared.viewModelFactory.homeViewModel(CancellableManager()).labelViewModel.text}"
        }
    }
}
