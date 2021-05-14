package movetotrikot

import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.subscribe
import com.mirego.trikot.viewmodels.declarative.ViewModel
import react.*

inline fun <T: ViewModel> useViewModelState(props: ViewModelComponentProp<T>): T {
    return useViewModelState(props.viewModel)
}

inline fun <T: ViewModel> useViewModelState(viewModel: T): T {
    val (delegate, setDelegate) = useState(Pair(viewModel, 0))
    useEffectWithCleanup {
        val cancellableManager = CancellableManager()
        viewModel.propertyDidChange.subscribe(cancellableManager) {
            setDelegate(Pair(viewModel, delegate.second + 1))
        }
        return@useEffectWithCleanup { cancellableManager.cancel() }
    }
    return delegate.first
}

fun <T: ViewModel> viewModelComponent(
    func: RBuilder.(viewModel: T) -> Unit
): FunctionalComponent<ViewModelComponentProp<T>> {
    return functionalComponent<ViewModelComponentProp<T>> { props ->
        val viewModel = useViewModelState(props.viewModel)
        func(viewModel)
    }
}

external interface ViewModelComponentProp<T: ViewModel>: RProps {
    var viewModel: T
}
