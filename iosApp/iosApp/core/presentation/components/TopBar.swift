//
//  TextBackButton.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TopBar: View {
    
    let backButtonText: String
    let onBackCkick: () -> Void
    
    var body: some View {
        HStack {
            TextBackButton(text: backButtonText, onClick: onBackCkick)
            Spacer()
            Button(action: {}){
                Image(systemName: "person.fill").font(.title2).foregroundColor(.onSecondary).padding(6).background(Color.grayish)
                    .clipShape(Circle())
            }
        }
    }
}

struct TextBackButton: View{
    
    var text:String
    var onClick:() -> Void
    
    var body: some View{
        Button(action: onClick){
            HStack{
                Image(systemName: "chevron.backward").font(.title2).foregroundColor(.onSecondary)
                Text(text).foregroundColor(.onSecondary).font(.title2).fontWeight(.regular)
            }
        }
    }
}

struct TextBackButton_Previews: PreviewProvider {
    static var previews: some View {
        TextBackButton(text:"History", onClick: {})
    }
}

struct TopBar_Previews: PreviewProvider {
    static var previews: some View {
        TopBar(backButtonText: "History", onBackCkick: {})
    }
}
