package components

import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDListViewModel
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageDescriptor
import movetotrikot.viewModelComponent
import react.dom.div
import react.dom.img
import react.router.dom.Link

val momentsComponent = viewModelComponent<VMDListViewModel<MomentViewModel>> { moments ->
    div(classes = "container grid grid-flow-row auto-rows-max") {
        for (moment in moments.elements) {
            Link {
                attrs.to = "/moments/${moment.identifier}"
                attrs.replace = true

                div(classes = "pb-4") {
                    +moment.title.text

                    div(classes = "relative border-2 rounded p-1") {
                        img(
                            classes = "rounded",
                            src = (moment.image.image as VMDImageDescriptor.Remote).url
                        ) {}
                    }
                }
            }
        }
    }
}
