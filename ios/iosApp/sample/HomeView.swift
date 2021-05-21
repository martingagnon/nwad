import NwadFramework
import SwiftUI
import Trikot_viewmodels_declarative
import struct Kingfisher.KFImage

struct HomeView: RootViewModelView {
    var viewModel: HomeViewModel
    @ObservedObject var textVM: ObservableViewModelAdapter<TextViewModel>
    @ObservedObject var moments: ObservableViewModelAdapter<ListViewModel<MomentViewModel>>

    init(viewModel: HomeViewModel) {
        self.viewModel = viewModel
        textVM = viewModel.labelViewModel.asObservable()
        moments = viewModel.moments.asObservable()
    }

    // swiftlint:disable all
    var body: some View {
        ScrollView {
            Button(action: { (UIApplication.shared.delegate as! AppDelegate).doSignIn() }) {
                Text("Log in")
            }
            .background(Color.blue)
            Text(textVM.viewModel.text)
            ForEach(moments.viewModel.elements, id: \.identifier) { moment in
                KFImage(URL(string: (moment.image.image as! ImageDescriptor.Remote).url))
                Text(moment.title.text)
            }
        }
    }
}
