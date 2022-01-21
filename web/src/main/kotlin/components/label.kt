package components

import com.mirego.trikot.viewmodels.declarative.components.VMDTextViewModel
import movetotrikot.viewModelComponent
import react.dom.html.ReactHTML.div

val labelComponent = viewModelComponent<VMDTextViewModel> { textViewModel ->
    div {
        className = "mb-3"

        +textViewModel.text
    }
}
