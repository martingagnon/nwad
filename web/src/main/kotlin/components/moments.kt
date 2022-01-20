package components

import com.mirego.nwad.viewmodels.home.MomentViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDListViewModel
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageDescriptor
import movetotrikot.viewModelComponent
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.router.dom.Link

val momentsComponent = viewModelComponent<VMDListViewModel<MomentViewModel>> { moments ->
    div {
        className = "container grid grid-flow-row auto-rows-max"

        for (moment in moments.elements) {
            Link {
                to = "/moments/${moment.identifier}"
                replace = true

                div {
                    className = "pb-4"

                    +moment.title.text

                    div {
                        className = "relative border-2 rounded p-1"

                        img {
                            className = "rounded"
                            src = (moment.image.image as VMDImageDescriptor.Remote).url
                        }
                    }
                }
            }
        }
    }
}
