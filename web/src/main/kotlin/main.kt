import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.http.web.WebHttpRequestFactory
import com.mirego.trikot.kword.js.JsKWord
import components.App
import kotlinext.js.require
import kotlinx.browser.document
import react.dom.div
import react.dom.render
import react.child

@ExperimentalJsExport
fun main() {
    require("bootstrap/dist/css/bootstrap.min.css")

    configureBasicSetup()

    setupLocalization()

    render(document.getElementById("root")) {
        div {
            child(App) {}
        }
    }
}

private fun configureBasicSetup() {
    HttpConfiguration.httpRequestFactory = WebHttpRequestFactory()
}

private fun setupLocalization() {
    JsKWord.setCurrentLanguageCode("en")
}
