import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()
    
    let appModule: AppModule = AppModule()


	var body: some View {
        ChatScreen(chatUseCase: appModule.chatUseCase, getLastChatIsUseCase: appModule.getLastChatIdUseCase)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
