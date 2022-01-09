package movetotrikot

import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.subscribe
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel
import react.FunctionComponent
import react.PropsWithChildren
import react.RBuilder
import react.functionComponent
import react.useEffect
import react.useState

fun <T: VMDViewModel> useViewModelState(props: ViewModelComponentProp<T>): T {
    return useViewModelState(props.viewModel)
}

fun <T: VMDViewModel> useViewModelState(viewModel: T): T {
    val (delegate, setDelegate) = useState(Pair(viewModel, 0))
    useEffect {
        val cancellableManager = CancellableManager()
        viewModel.propertyDidChange.subscribe(cancellableManager) {
            setDelegate(Pair(viewModel, delegate.second + 1))
        }
        return@useEffect cleanup { cancellableManager.cancel() }
    }
    return delegate.first
}

fun <T: VMDViewModel> viewModelComponent(
    func: RBuilder.(viewModel: T) -> Unit
): FunctionComponent<ViewModelComponentProp<T>> {
    return functionComponent { props ->
        val viewModel = useViewModelState(props.viewModel)
        func(viewModel)
    }
}

external interface ViewModelComponentProp<T: VMDViewModel>: PropsWithChildren {
    var viewModel: T
}
