package components

import kotlinext.js.require
import react.FunctionComponent
import react.PropsWithChildren
import react.dom.a
import react.dom.div
import react.dom.nav
import react.functionComponent

external interface ImageProps : PropsWithChildren {
    var width: String?
    var height: String?
}

@Suppress("UnsafeCastFromDynamic")
val nwadLogo: FunctionComponent<ImageProps> = require("./images/nwad-logo.svg").default

val NavBar = functionComponent<PropsWithChildren> {
    nav(classes = "bg-white border border-gray-300") {
        div(classes = "mx-auto px-2") {
            div(classes = "relative flex items-center h-16") {
                div(classes = "pl-2") {
                    child(nwadLogo) {
                        attrs.width = "24px"
                        attrs.height = "24px"
                    }
                }

                a(classes = "text-gray-600 px-3 text-xl font-medium", href = "/") {
                    +"Never Work A Day"
                }
            }
        }
    }
}
