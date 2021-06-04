import NwadFramework
import SwiftUI
import Trikot_viewmodels_declarative

struct CreateMomentView: View {
    var viewModel: CreateMomentViewModel = Bootstrap().viewModelFactory.createMomentViewModel(cancellableManager: CancellableManager())
    @State private var inputImage: UIImage?
    @State private var sourceType: UIImagePickerController.SourceType = .photoLibrary
    @State var shouldShowImagePicker = false

    var body: some View {
        VStack {
            VMTextView(viewModel.titleInput)
                .background(Color.white)
                .frame(width: 100, height: 44)
            Button(action: { shouldShowImagePicker = true }) {
                if let image = inputImage {
                    Image(uiImage: image)
                        .resizable()
                        .clipped()
                } else {
                    Color.gray
                }
            }
            .frame(width: 100, height: 100)

            HStack {
                TextButton(viewModel.submitButton)
                    .background(Color.yellow)
                    .frame(width: 100, height: 44)
            }
            
        }
        .sheet(isPresented: self.$shouldShowImagePicker) {
            ImagePicker(self.viewModel, image: self.$inputImage, sourceType: self.sourceType)
        }
    }

    struct TextButton: View {
        @ObservedObject var buttonViewModel: ObservableViewModelAdapter<ButtonViewModel<TextViewModelContent>>

        init(_ buttonViewModel: ButtonViewModel<TextViewModelContent>) {
            self.buttonViewModel = buttonViewModel.asObservable()
        }

        var body: some View {
            Button(action: { buttonViewModel.viewModel.action() }) {
                Text("Submit")
            }
            .disabled(!buttonViewModel.viewModel.enabled)
        }
    }
}
