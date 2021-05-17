package components

import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.viewmodels.declarative.components.ListViewModel
import com.mirego.trikot.viewmodels.declarative.properties.ImageDescriptor
import movetotrikot.viewModelComponent
import react.dom.div
import react.dom.img

val momentsComponent = viewModelComponent<ListViewModel<MomentViewModel>> { moments ->
    div(classes = "container grid grid-flow-row auto-rows-max") {
        for (moment in moments.elements) {
            div(classes = "pb-4") {
                +moment.title.text

                div(classes = "relative border-2 rounded p-1") {
                    img(
                        classes = "rounded",
                        src = (moment.image.image as ImageDescriptor.Remote).url
                    ) {}
                }
            }
        }
    }
}
