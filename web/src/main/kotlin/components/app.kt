package components

import react.PropsWithChildren
import react.dom.div
import react.functionComponent
import react.router.dom.BrowserRouter
import react.router.dom.Route
import react.router.dom.Switch

@ExperimentalJsExport
val App = functionComponent<PropsWithChildren> {
    BrowserRouter {
        child(NavBar)

        div(classes = "background") {
            Switch {
                Route {
                    attrs.path = arrayOf("/moments/:momentId")
                    child(Moment)
                }

                Route {
                    attrs.path = arrayOf("/")
                    attrs.exact = true
                    child(Home)
                }

                Route {
                    attrs.path = arrayOf("*")
                    child(Error404NotFound)
                }
            }
        }
    }
}
