import Foundation
import NwadFramework
import Photos
import SwiftUI

struct ImagePicker: UIViewControllerRepresentable {
    @SwiftUI.Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    let viewModel: ImagePickerViewModel
    @Binding var selectedImage: UIImage?
    let sourceType: UIImagePickerController.SourceType

    init(_ viewModel: ImagePickerViewModel, image: Binding<UIImage?>, sourceType: UIImagePickerController.SourceType = .photoLibrary) {
        self.viewModel = viewModel
        self._selectedImage = image
        self.sourceType = sourceType

        switch sourceType {
        case .photoLibrary:
            let status = PHPhotoLibrary.authorizationStatus()
            if status == .notDetermined {
                PHPhotoLibrary.requestAuthorization { _ in }
            }
        case .camera:
            let status = AVCaptureDevice.authorizationStatus(for: .video)
            if status == .notDetermined {
                AVCaptureDevice.requestAccess(for: .video) { _ in }
            }
        case .savedPhotosAlbum:
            return
        @unknown default:
            return
        }
    }

    func makeUIViewController(context: UIViewControllerRepresentableContext<ImagePicker>) -> UIImagePickerController {
        let picker = UIImagePickerController()
        picker.sourceType = sourceType
        picker.delegate = context.coordinator
        return picker
    }

    func updateUIViewController(_ uiViewController: UIImagePickerController, context: UIViewControllerRepresentableContext<ImagePicker>) {

    }

    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }

    class Coordinator: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
        let parent: ImagePicker

        init(_ parent: ImagePicker) {
            self.parent = parent
        }

        func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]) {
            if let uiImage = info[.originalImage] as? UIImage {
                let resizedImage = resizeImage(image: uiImage)
                if let data = resizedImage.jpegData(compressionQuality: 1) {
                    self.parent.selectedImage = resizedImage
                    DispatchQueue.main.async {
                        let kotlinData = ByteArrayNativeUtils().convert(data: data)
                        self.parent.viewModel.onImageSelected(data: kotlinData)
                    }
                }
            }

            parent.presentationMode.wrappedValue.dismiss()
        }

        func resizeImage(image: UIImage) -> UIImage {
            let width = image.size.width
            let height = image.size.height
            UIGraphicsBeginImageContext(CGSize(width: width, height: height))
            image.draw(in: CGRect(x: 0, y: 0, width: width, height: height))
            let newImage = UIGraphicsGetImageFromCurrentImageContext()
            UIGraphicsEndImageContext()

            return newImage!
        }
    }
}
