//
//  GoButton.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct GoButton: View {
    
    let onClick: ()->Void
    let enable: Bool
    
    var body: some View {
        Button(action: onClick){
            Text("Go")
                .font(.title2).fontWeight(.medium)
                .foregroundColor(.white)
                .padding(.horizontal)
                .padding(.vertical, 8)
        }.background{
            let shape = RoundedRectangle(cornerRadius: 32)
                
            
            if enable {
                shape.fill(Color.darkBlue)
            }else {
                shape.fill(Color.primaryVarient)
            }
        }.disabled(!enable)
    }
}

struct GoButton_Previews: PreviewProvider {
    static var previews: some View {
        GoButton(onClick: {}, enable: false)
    }
}
