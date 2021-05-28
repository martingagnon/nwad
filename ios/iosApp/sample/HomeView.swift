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
            LoginButton(viewModel.loginButton)
            LogoutButton(viewModel.logoutButton)
            Text(textVM.viewModel.text)
            ForEach(moments.viewModel.elements, id: \.identifier) { moment in
                KFImage(URL(string: (moment.image.image as! ImageDescriptor.Remote).url))
                Text(moment.title.text)
            }
        }
    }

    struct LoginButton: ViewModelView {
        var viewModel: ButtonViewModel<TextViewModelContent>
        @ObservedObject var ovm: ObservableViewModelAdapter<ButtonViewModel<TextViewModelContent>>

        init(_ viewModel: ButtonViewModel<TextViewModelContent>) {
            self.viewModel = viewModel
            self.ovm = viewModel.asObservable()
        }

        var body: some View {
            if viewModel.hidden {
                EmptyView()
            } else {
                Button(action: { (UIApplication.shared.delegate as! AppDelegate).doSignIn() }) {
                    Text("Log in")
                        .foregroundColor(Color.black)
                }
                .background(Color.blue)
            }
        }
    }

    struct LogoutButton: ViewModelView {
        var viewModel: ButtonViewModel<TextViewModelContent>
        @ObservedObject var ovm: ObservableViewModelAdapter<ButtonViewModel<TextViewModelContent>>

        init(_ viewModel: ButtonViewModel<TextViewModelContent>) {
            self.viewModel = viewModel
            self.ovm = viewModel.asObservable()
        }

        var body: some View {
            if viewModel.hidden {
                EmptyView()
            } else {
                Button(action: { viewModel.action() }) {
                    Text("Logout")
                        .foregroundColor(Color.black)
                }
                .background(Color.red)
            }
        }
    }
}
