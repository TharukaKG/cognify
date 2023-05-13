//
//  ChatTextField.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-09.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ChatTextField: View {
    
    @Binding var question:String
    let onClear: ()->Void
    
    var body: some View {
        
        VStack{
            // close button
            Button(action: onClear){
                Image(systemName: "xmark")
                    .foregroundColor(.primaryVarient)
                    .frame(maxWidth: .infinity, alignment: .trailing)
                    .padding(.trailing, 16)
                    .padding(.top, 16)
            }
            // chat text field
            TextField("Ask Anything", text: $question).foregroundColor(.primaryVarient).padding(8)
            
        }
    }
}


struct ChatTextField_Previews: PreviewProvider {
    static var previews: some View {
        ChatTextField(
            question: Binding(get: {"Hi, What is quantum physicss"}, set: {valte in }),
            onClear: {})
    }
}
