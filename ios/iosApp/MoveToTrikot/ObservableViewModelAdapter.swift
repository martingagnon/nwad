//import Combine
//import NwadFramework
//import UIKit

//class ObservableViewModelAdapter<VM: ViewModel>: ObservableObject {
//    let viewModel: VM
//    private let propertyWillChangePublisher: AnyPublisher<KotlinUnit, Never>
//    private var cancellable: AnyCancellable?
//
//    init(viewModel: VM) {
//        self.viewModel = viewModel
//        self.propertyWillChangePublisher = viewModel.propertyWillChange.eraseToAnyPublisher()
//        self.cancellable = propertyWillChangePublisher
//            .receive(on: Foundation.DispatchQueue.main)
//            .sink { [weak self] _ in
//                self?.objectWillChange.send()
//            }
//    }
//
//    deinit {
//        cancellable?.cancel()
//    }
//}
//
//extension ViewModel {
//    func asObservable<Self>() -> ObservableViewModelAdapter<Self> {
//        ObservableViewModelAdapter(viewModel: self as! Self)
//    }
//}
//
