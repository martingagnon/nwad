import Foundation
import NwadFramework

class Core {
    static let shared = Core()
    private var bootstrap = Bootstrap()
    let viewModelControllerFactory: ViewModelControllerFactory

    init() {
        viewModelControllerFactory = bootstrap.viewModelControllerFactory
    }
}
