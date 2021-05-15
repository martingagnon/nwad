package components

import com.mirego.trikot.viewmodels.declarative.components.TextViewModel
import movetotrikot.viewModelComponent
import react.dom.div

val labelComponent = viewModelComponent<TextViewModel> { textViewModel ->
    div(classes = "mb-3") {
        +textViewModel.text
    }
}
