package com.tharuka.cognify.android.chat.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Composable
fun GeneratingAnimation(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading_circle.json"))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

    Box(modifier = modifier.fillMaxWidth(), contentAlignment =Alignment.CenterEnd){
        LottieAnimation(
            modifier = Modifier.size(32.dp),
            composition = composition,
            progress = { progress }
        )
    }
}
