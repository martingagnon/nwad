package components

import react.FC
import react.Fragment
import react.PropsWithChildren
import react.dom.html.ReactHTML.div

@ExperimentalJsExport
val Error404NotFound = FC<PropsWithChildren> {
    Fragment {
        div {
            className = "container mx-auto p-4 text-gray-500"

            div {
                className = "mb-3 text-3xl font-semibold"
                +"Page not found"
            }
            div {
                className = "text-sm"
                +"We could not find the page you were looking for."
            }
        }
    }
}
