package components

import react.RProps
import react.dom.a
import react.dom.div
import react.dom.nav
import react.functionalComponent

val NavBar = functionalComponent<RProps> {
    nav(classes = "navbar navbar-expand-lg navbar-light bg-light") {
        div(classes = "container-fluid") {
            a(classes = "navbar-brand", href = "#") {
                +"Never Work A Day"
            }
        }
    }
}
