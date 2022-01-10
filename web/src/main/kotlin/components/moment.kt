package components

import react.Fragment
import react.PropsWithChildren
import react.dom.div
import react.functionComponent
import react.router.dom.useParams

@ExperimentalJsExport
val Moment = functionComponent<PropsWithChildren> {
    val momentId = useParams()["momentId"] ?: return@functionComponent

    Fragment {
        div(classes = "container mx-auto px-4") {
            +"Moment $momentId"
        }
    }
}
