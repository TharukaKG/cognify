//
//  ChatScreen.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared



struct ChatScreen: View {
    
    @ObservedObject var viewModel: IOSChatViewModel
    private let chatUseCase: ChatUseCase
    private let getLastChatIsUseCase: GetLastChatIdUseCase
    private let chatId: String?
    private let parser: VoiceToTextParser = IOSVoiceToTextParser()
    
    init(chatUseCase: ChatUseCase, getLastChatIsUseCase: GetLastChatIdUseCase, chatId: String? = nil) {
        
        self.chatUseCase = chatUseCase
        self.getLastChatIsUseCase = getLastChatIsUseCase
        self.chatId = chatId
        
        self.viewModel = IOSChatViewModel(chatUseCase: self.chatUseCase, getLastChatIdUseCase: self.getLastChatIsUseCase, parser: self.parser, chatId: self.chatId)
    }
    
    var body: some View {
        VStack{
            // top bar
            TopBar(backButtonText: "History",onBackCkick: {viewModel.onEvent(chatEvent: ChatEvent.GotoHistory())})
            
            Spacer()
            // chat view
            ChatView(
                question: Binding(
                    get: {viewModel.state.question},
                    set: {value in viewModel.onEvent(chatEvent: ChatEvent.OnTypeQuestion(question: value))}
                ),
                answer: viewModel.state.answer ?? "",
                generatingStatus: viewModel.state.generatingStatus,
                onClearQuestion: {viewModel.onEvent(chatEvent: ChatEvent.ClearQuestion())},
                onClose: {viewModel.onEvent(chatEvent: ChatEvent.CloseChatResult())},
                onCopy: { text in}
            )
            
            Spacer()
            //action bar
            ZStack{
                // speak button
                HStack{
                    SpeakButton(voiceToTextStatus: viewModel.state.voiceToTextState, onCLick: {})
                    Spacer()
                }
                //go button
                GoButton(onClick: {viewModel.onEvent(chatEvent: ChatEvent.Go())}, enable: !viewModel.state.question.isEmpty)
                
                
            }.frame(maxWidth: .infinity)
        }.padding(.horizontal)
            .onAppear{viewModel.startObserving()}
            .onDisappear{viewModel.dispose()}
    }
}

//struct ChatScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        ChatScreen()
//    }
//}
