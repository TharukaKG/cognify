//
//  Color.swift
//  iosApp
//
//  Created by Tharuka Gamage on 2023-05-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension Color{
    init(hex: Int64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
    
    private static let colors = Colors()
    static let darkBlue = Color(hex: colors.DarkBlue)
    static let darkBlueText = Color(hex: colors.DarkBlueText)
    static let whitish = Color(hex: colors.Whitish)
    static let grayish = Color(hex: colors.Grayish, alpha: 40)
    static let lightBlue = Color(hex: colors.LightBlue, alpha: 40)
    static let lightBlueText = Color(hex: colors.LightBlueText)
    
    static let primary = Color(light: .darkBlue, dark: .darkBlue)
    static let onPrimary = Color(light: .white, dark: .white)
    static let primaryVarient = Color(light: .darkBlueText, dark: .darkBlueText)
    static let background = Color(light: .whitish, dark: .whitish)
    static let onBackground = Color(light: .lightBlue, dark: .lightBlue)
    static let secondary = Color(light: .lightBlue, dark: .lightBlue)
    static let onSecondary = Color(light: .lightBlueText, dark: .lightBlueText)
    static let surface = Color(light: .white, dark: .white)
    static let secondaryVarient = Color(light: .grayish, dark: .grayish)
}

private extension Color {
    init(light: Self, dark: Self) {
        self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(dark)))
    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}



