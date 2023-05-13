//
//  SpeakButton.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SpeakButton: View {
    
    let voiceToTextStatus:VoiceToTextStatus
    let onCLick: ()-> Void
    
    var body: some View {
        
        ZStack{
            if voiceToTextStatus != VoiceToTextStatus.speaking{
                Button(action: onCLick){
                    Image(systemName: "mic")
                        .padding(12)
                        .foregroundColor(.white)
                        .font(.title3)
                        .background{
                            Circle().fill(Color.darkBlue)
                        }
                }
            }else{
                Text("Listening...")
                    .padding(.vertical, 12)
                    .padding(.horizontal, 12)
                    .foregroundColor(.white)
                    .font(.title3)
                    .background{
                        RoundedRectangle(cornerRadius: 32).fill(Color.darkBlue)
                    }
            }
        }.disabled(
            voiceToTextStatus != VoiceToTextStatus.idle ||
            voiceToTextStatus != VoiceToTextStatus.displayingResults
        )
    }
}

struct SpeakButton_Previews: PreviewProvider {
    static var previews: some View {
        SpeakButton(voiceToTextStatus: VoiceToTextStatus.speaking, onCLick: {})
    }
}
