package components

import react.FC
import react.Fragment
import react.PropsWithChildren
import react.dom.html.ReactHTML.div
import react.router.useParams

@ExperimentalJsExport
val Moment = FC<PropsWithChildren> {
    val momentId = useParams()["momentId"] ?: return@FC

    Fragment {
        div {
            className = "container mx-auto px-4"

            +"Moment $momentId"
        }
    }
}
