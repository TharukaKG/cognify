//
//  ChatView.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ChatView: View {
    
    @Binding var question:String
    let answer:String
    let generatingStatus:GeneratingStatus
    let onClearQuestion:()->Void
    let onClose:()->Void
    let onCopy:(String)->Void
    
    var body: some View {
        ZStack{
            if generatingStatus == GeneratingStatus.generated {
                ChatResult(
                    question: question,
                    answer: answer,
                    onClose: onClose,
                    onCopy: onCopy
                )
            }else {
                ChatTextField(
                    question: $question,
                    onClear: onClearQuestion
                )
            }
        }.background(
            RoundedRectangle(cornerRadius: 8)
                .fill(Color.grayish)
        ).animation(.easeInOut(duration: 0.25), value: generatingStatus)
    }
}

struct ChatView_Previews: PreviewProvider {
    static var previews: some View {
        ChatView(question: Binding(get: {"What is quantum physics"}, set: {value in}), answer: "Quantum physics is ...", generatingStatus: GeneratingStatus.idle, onClearQuestion: {}, onClose: {}, onCopy: {value in})
    }
}
