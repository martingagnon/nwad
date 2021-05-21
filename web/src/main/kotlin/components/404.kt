package components

import react.Fragment
import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch

@ExperimentalJsExport
val Error404NotFound = functionalComponent<RProps> {
    Fragment {
        div(classes = "container mx-auto p-4 text-gray-500") {
            div(classes = "mb-3 text-3xl font-semibold") {
                +"Page not found"
            }
            div(classes = "text-sm") {
                +"We could not find the page you were looking for."
            }
        }
    }
}
