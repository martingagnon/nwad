package components

import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.viewmodels.declarative.components.ListViewModel
import com.mirego.trikot.viewmodels.declarative.properties.ImageDescriptor
import movetotrikot.viewModelComponent
import react.dom.div
import react.dom.img

val momentsComponent = viewModelComponent<ListViewModel<MomentViewModel>> { moments ->
    div(classes = "container") {
        for (moment in moments.elements) {
            div(classes = "row mb-3") {
                div(classes = "col") {
                    +moment.title.text

                    div {
                        img(
                            classes = "img-thumbnail rounded d-block",
                            src = (moment.image.image as ImageDescriptor.Remote).url
                        ) {}
                    }
                }
            }
        }
    }
}
