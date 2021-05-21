import GoogleSignIn
import NwadFramework
import Trikot_http
import Trikot_kword
import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    //swiftlint:disable discouraged_optional_collection
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        Environment().flavor = currentFlavor()
        HttpConfiguration().httpRequestFactory = TrikotHttpRequestFactory()
        HttpConfiguration().connectivityPublisher = TrikotConnectivityService.shared.publisher
        TrikotKword.shared.setCurrentLanguage("en")

        let window = UIWindow(frame: UIScreen.main.bounds)
        self.window = window

        GIDSignIn.sharedInstance().clientID = "768209214998-d30s4gfilfflt4erkaok8hpdl05chn4u.apps.googleusercontent.com"
        GIDSignIn.sharedInstance().delegate = self

        window.rootViewController = HomeViewController(viewModelController: Bootstrap().viewModelControllerFactory.createHome())

        window.makeKeyAndVisible()

        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {
        TrikotConnectivityService.shared.stop()
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
    }

    func applicationWillEnterForeground(_ application: UIApplication) {}

    func applicationDidBecomeActive(_ application: UIApplication) {
        TrikotConnectivityService.shared.start()
    }

    func applicationWillTerminate(_ application: UIApplication) {}

    func currentFlavor() -> Environment.Flavor {
        switch (Bundle.main.object(forInfoDictionaryKey: "Environment") ?? "debug") as! String {
        case "debug": return .debug
        case "qa": return .qa
        case "staging": return .staging
        default: return Environment.Flavor.release_
        }
    }

    func doSignIn() {
        guard let window = window else { return }
        GIDSignIn.sharedInstance().presentingViewController = window.rootViewController
        GIDSignIn.sharedInstance().signIn()
    }
}

extension AppDelegate: GIDSignInDelegate {
    func sign(_ signIn: GIDSignIn!, didSignInFor user: GIDGoogleUser!, withError error: Error!) {

        if error == nil {
            Bootstrap().viewModelFactory.loginViewModel().login(token: user.authentication.idToken)
                .onSuccess { _ in
                    print("Logged")
                }
                .onError { _ in
                    print("Could not log in")
                }
        } else {
            print("Error Google sign in")
        }
    }
}
