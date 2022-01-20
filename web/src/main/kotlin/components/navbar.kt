package components

import kotlinext.js.jso
import kotlinext.js.require
import react.FC
import react.PropsWithChildren
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.nav

external interface ImageProps : PropsWithChildren {
    var width: String?
    var height: String?
}

@Suppress("UnsafeCastFromDynamic")
val nwadLogo: FC<ImageProps> = require("./images/nwad-logo.svg").default

val NavBar = FC<PropsWithChildren> {
    nav {
        className = "bg-white border border-gray-300"

        div {
            className = "mx-auto px-2"

            div {
                className = "relative flex items-center h-16"

                div {
                    className = "pl-2"

                    child(nwadLogo, jso {
                        width = "24px"
                        height = "24px"
                    })

                    a {
                        className = "text-gray-600 px-3 text-xl font-medium"
                        href = "/"
                        +"Never Work A Day"
                    }
                }
            }
        }
    }
}
