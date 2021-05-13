import NwadFramework
import SwiftUI
import Trikot_viewmodels_declarative

struct HomeView: RootViewModelView {
    var viewModel: HomeViewModel
    @ObservedObject var textVM: ObservableViewModelAdapter<TextViewModel>

    init(viewModel: HomeViewModel) {
        self.viewModel = viewModel
        textVM = viewModel.labelViewModel.asObservable()
    }

    var body: some View {
        Text(textVM.viewModel.text)
    }
}
