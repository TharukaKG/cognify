//
//  ChatResult.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-10.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ChatResult: View {
    
    let question:String
    let answer:String
    let onClose:()->Void
    let onCopy:(String)->Void
    
    var body: some View {
        VStack(alignment: .leading){
            
            // close button
            Button(action: onClose){
                Image(systemName: "xmark")
                    .foregroundColor(.primaryVarient)
                    .frame(maxWidth: .infinity, alignment: .trailing)
                    .padding(.trailing, 16)
                    .padding(.top, 16)
                    .padding(.bottom, 8)
            }
            //question
            Text(question)
                .foregroundColor(.primaryVarient)
                .font(.body)
            
            //divider
            Divider()
            
            //answer
            Text(answer)
                .padding(8)
                .padding(.bottom, 32)
                .frame(maxWidth: .infinity, alignment: .leading)
                .font(.body)
                .foregroundColor(.primaryVarient)
                .background(
                    RoundedRectangle(cornerRadius: 4)
                        .fill(Color.onBackground)
                    
                )
                .overlay(alignment: .bottomTrailing){
                    Button(action: onClose){
                        Image(systemName: "doc.on.doc")
                            .foregroundColor(.primaryVarient)
                            .padding(.trailing)
                            .padding(.bottom, 8)
                    }
                }
            
        }.padding(8)
    }
}

struct ChatResult_Previews: PreviewProvider {
    static var previews: some View {
        ChatResult(
            question: "Hi, How are you", answer: "As an AI model I don't have feelings", onClose: {}, onCopy: {value in}
        )
    }
}
