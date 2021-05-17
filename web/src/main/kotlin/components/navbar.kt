package components

import react.RProps
import react.dom.a
import react.dom.div
import react.dom.nav
import react.functionalComponent

val NavBar = functionalComponent<RProps> {
    nav(classes = "bg-gray-800") {
        div(classes = "mx-auto px-2") {
            div(classes = "relative flex items-center justify-between h-16") {
                a(classes = "text-white px-3 text-xl font-medium", href = "#") {
                    +"Never Work A Day"
                }
            }
        }
    }
}
