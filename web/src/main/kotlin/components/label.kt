package components

import com.mirego.trikot.viewmodels.declarative.components.VMDTextViewModel
import movetotrikot.viewModelComponent
import react.dom.div

val labelComponent = viewModelComponent<VMDTextViewModel> { textViewModel ->
    div(classes = "mb-3") {
        +textViewModel.text
    }
}
