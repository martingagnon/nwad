package movetotrikot

import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.subscribe
import com.mirego.trikot.viewmodels.declarative.ViewModel
import react.RProps
import react.useEffectWithCleanup
import react.useState

inline fun <T: ViewModel> useViewModelState(viewModel: T): T {
    val (delegate, setDelegate) = useState(Pair(viewModel, 0))
    val cancellableManager = CancellableManager()
    useEffectWithCleanup {
        viewModel.propertyDidChange.subscribe(cancellableManager) {
            setDelegate(Pair(viewModel, delegate.second + 1))
        }

        return@useEffectWithCleanup { cancellableManager.cancel() }
    }
    return delegate.first
}

external interface ViewModelComponentProp<T: ViewModel>: RProps {
    var viewModel: T
}
