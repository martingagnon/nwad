import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.http.web.WebHttpRequestFactory
import components.App
import kotlinext.js.require
import kotlinx.browser.document
import react.create
import react.dom.render

@ExperimentalJsExport
fun main() {
    require("./css/tailwind.css")
    require("./css/app.css")

    val body = checkNotNull(document.body)
    val container = document.createElement("div").also(body::appendChild)

    configureBasicSetup()

    setupLocalization()

    render(App.create(), container)
}

private fun configureBasicSetup() {
    HttpConfiguration.httpRequestFactory = WebHttpRequestFactory()
}

private fun setupLocalization() {
    // TODO: Uncomment once we start using i18n
//    JsKWord.setCurrentLanguageCode("en")
}
