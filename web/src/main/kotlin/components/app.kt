package components

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch

@ExperimentalJsExport
val App = functionalComponent<RProps> {
    browserRouter {
        child(NavBar)

        div(classes = "background") {
            switch {
                route("/", exact = true) { child(Home) }
                route("/moments/:momentId") { child(Moment) }
                route("*") { child(Error404NotFound) }
            }
        }
    }
}
