//
//  IOSChatViewModel.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

@MainActor class IOSChatViewModel: ObservableObject{
    
    private var chatUseCase: ChatUseCase
    private var getLastChatIdUseCase: GetLastChatIdUseCase
    private var parser: VoiceToTextParser
    private var chatId: String?
    
    private var viewModel: ChatViewModel
    @Published var state: ChatState = ChatState(
        chatId: nil,
        error: nil,
        question: "",
        answer: nil,
        canRecord: true,
        generatingStatus: GeneratingStatus.idle,
        voiceToTextState: VoiceToTextStatus.idle
    )
    
    private var handle: DisposableHandle?
    
    init(chatUseCase: ChatUseCase, getLastChatIdUseCase: GetLastChatIdUseCase, parser: VoiceToTextParser, chatId: String?) {
        self.chatUseCase = chatUseCase
        self.getLastChatIdUseCase = getLastChatIdUseCase
        self.parser = parser
        self.chatId = chatId
        
        self.viewModel = ChatViewModel(chatUseCase: self.chatUseCase, getLastChatIdUseCase: self.getLastChatIdUseCase, parser: self.parser, chatId: chatId, coroutineScope: nil)
        
        self.viewModel.onChatEvent(event: ChatEvent.PermissionResult(isGRanted: true, isPermanentlyDeclined: false))
    }
    
    func onEvent(chatEvent: ChatEvent){
        viewModel.onChatEvent(event: chatEvent)
    }
    
    func startObserving(){
        handle = viewModel.state.subscribe{ [weak self] state in
            if let state{
                self?.state = state
            }
        }
    }
    
    func dispose(){
        handle?.dispose()
    }
    
}
