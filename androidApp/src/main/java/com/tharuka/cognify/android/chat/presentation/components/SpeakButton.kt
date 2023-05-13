@file:OptIn(ExperimentalAnimationApi::class)

package com.tharuka.cognify.android.chat.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tharuka.cognify.android.R
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.chat.presentation.VoiceToTextStatus

@Composable
fun SpeakButton(
    voiceToTextStatus: VoiceToTextStatus?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .defaultMinSize(48.dp)
            .clip(CircleShape)
            .background(
                color = MaterialTheme.colors.primary
            )
            .clickable(onClick = {
                if (voiceToTextStatus == VoiceToTextStatus.IDLE || voiceToTextStatus == VoiceToTextStatus.DISPLAYING_RESULTS) {
                    onClick()
                }
            }),
        contentAlignment = Alignment.Center
    ) {

        AnimatedContent(targetState = voiceToTextStatus) {state ->
            when(state){
                VoiceToTextStatus.SPEAKING -> {
                    Text(
                        text = stringResource(id = R.string.listening),
                        style = MaterialTheme.typography.button.copy(fontSize = 18.sp),
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
                else -> {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_mic),
                        contentDescription = stringResource(id = R.string.speak),
                        modifier = Modifier.size(26.dp),
                        tint = MaterialTheme.colors.onPrimary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSpeakButton() {
    CognifyTheme {
        SpeakButton(voiceToTextStatus = VoiceToTextStatus.IDLE , onClick = {})
    }
}