import NwadFramework
import SwiftUI
import Trikot_viewmodels_declarative
import UIKit

// swiftlint:disable all
struct VMTextView: UIViewRepresentable {
    private let viewModel: TextFieldViewModel

    @State private var isPlaceholderVisible: Bool = true
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<TextFieldViewModel>

    private let text: Binding<String>
    private let isFirstResponder: Binding<Bool?>?
    private let preferredHeight: Binding<CGFloat>?

    private let font: UIFont?
    private let tintColor: UIColor?
    private let textColor: UIColor
    private let placeholderColor: UIColor

    init(
        _ viewModel: TextFieldViewModel,
         isFirstResponder: Binding<Bool?>? = nil,
         preferredHeight: Binding<CGFloat>? = nil,
         font: UIFont? = UIFont.systemFont(ofSize: 14),
         tintColor: UIColor? = nil,
         textColor: UIColor = .black,
         placeholderColor: UIColor = .gray
    ) {
        self.viewModel = viewModel
        self.observableViewModel = viewModel.asObservable()
        self.text = Binding(get: {
            return viewModel.text
        }, set: { newText in
            viewModel.onValueChange(text: newText)
        })

        self.isFirstResponder = isFirstResponder
        self.preferredHeight = preferredHeight
        self.font = font
        self.tintColor = tintColor
        self.textColor = textColor
        self.placeholderColor = placeholderColor

        self.isPlaceholderVisible = viewModel.text.isEmpty
    }

    func makeUIView(context: UIViewRepresentableContext<VMTextView>) -> UITextView {
        let textView = UITextView()
        textView.font = font
        textView.tintColor = tintColor
        textView.textColor = textColor
        textView.backgroundColor = .clear
        textView.delegate = context.coordinator
        textView.textAlignment = .left
        return textView
    }

    func makeCoordinator() -> VMTextView.Coordinator {
        Coordinator(text: text, isPlaceholderVisible: $isPlaceholderVisible)
    }

    func updateUIView(_ uiView: UITextView, context: UIViewRepresentableContext<VMTextView>) {
        uiView.text = isPlaceholderVisible ? viewModel.placeholder : text.wrappedValue
        uiView.textColor = isPlaceholderVisible ? placeholderColor : textColor

        if let shouldBecomeFirstResponder = isFirstResponder?.wrappedValue {
            DispatchQueue.main.async {
                if shouldBecomeFirstResponder && !uiView.isFirstResponder {
                    uiView.becomeFirstResponder()
                } else if !shouldBecomeFirstResponder && uiView.isFirstResponder {
                    uiView.resignFirstResponder()
                }
            }
        }

        if let preferredHeight = self.preferredHeight {
            DispatchQueue.main.async {
                let fixedWidth = uiView.frame.size.width
                let newSize = uiView.sizeThatFits(CGSize(width: fixedWidth, height: CGFloat.greatestFiniteMagnitude))

                preferredHeight.wrappedValue = newSize.height
            }
        }
    }

    class Coordinator: NSObject, UITextViewDelegate {
        @Binding var text: String
        @Binding var isPlaceholderVisible: Bool

        init(text: Binding<String>, isPlaceholderVisible: Binding<Bool>) {
            _text = text
            _isPlaceholderVisible = isPlaceholderVisible
        }

        func textViewDidChange(_ textView: UITextView) {
            if !isPlaceholderVisible {
                text = textView.text
            }
        }

        func textViewDidBeginEditing(_ textView: UITextView) {
            if isPlaceholderVisible {
                isPlaceholderVisible = false
                textView.text = nil
            }
        }

        func textViewDidEndEditing(_ textView: UITextView) {
            if textView.text.isEmpty {
                isPlaceholderVisible = true
            }
        }
    }
}
