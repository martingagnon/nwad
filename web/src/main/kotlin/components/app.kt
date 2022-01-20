package components

import react.FC
import react.PropsWithChildren
import react.create
import react.createElement
import react.dom.html.ReactHTML.div
import react.router.dom.BrowserRouter
import react.router.Routes
import react.router.Route

@ExperimentalJsExport
val App = FC<PropsWithChildren> {
    BrowserRouter {
        NavBar

        div {
            className = "background"

            Routes {
                Route {
                    index = true
                    element = createElement(Home)
                }

                Route {
                    path = "/moments/:momentId"
                    element = createElement(Moment)
                }

                Route {
                    path = "*"
                    element = createElement(Error404NotFound)
                }
            }
        }
    }
}
