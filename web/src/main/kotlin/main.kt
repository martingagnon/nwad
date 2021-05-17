import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.http.web.WebHttpRequestFactory
import components.App
import kotlinext.js.require
import kotlinx.browser.document
import react.dom.div
import react.dom.render
import react.child

@ExperimentalJsExport
fun main() {
    require("./css/tailwind.css")
    require("./css/app.css")

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
    // TODO: Uncomment once we start using i18n
//    JsKWord.setCurrentLanguageCode("en")
}
