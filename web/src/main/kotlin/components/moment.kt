package components

import react.Fragment
import react.RProps
import react.dom.div
import react.functionalComponent
import react.router.dom.useParams

external interface MomentParams : RProps {
    var momentId: String
}

@ExperimentalJsExport
val Moment = functionalComponent<RProps> {
    val momentId = useParams<MomentParams>()?.momentId ?: return@functionalComponent

    Fragment {
        div(classes = "container mx-auto px-4") {
            +"Moment $momentId"
        }
    }
}
