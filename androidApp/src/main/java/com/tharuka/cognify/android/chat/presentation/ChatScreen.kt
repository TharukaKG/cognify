package com.tharuka.cognify.android.chat.presentation

import android.Manifest
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tharuka.cognify.android.R
import com.tharuka.cognify.android.chat.presentation.components.ChatView
import com.tharuka.cognify.android.chat.presentation.components.GoButton
import com.tharuka.cognify.android.chat.presentation.components.SpeakButton
import com.tharuka.cognify.android.core.presentatiton.components.TopBar
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.chat.presentation.ChatEvent
import com.tharuka.cognify.chat.presentation.ChatState

@Composable
fun PromptScreen(
    state: ChatState,
    onEvent: (ChatEvent) -> Unit
) {

    val clipBoardManager = LocalClipboardManager.current
    val context = LocalContext.current

    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            onEvent(
                ChatEvent.PermissionResult(
                    isGRanted = isGranted,
                    isPermanentlyDeclined = !isGranted && (context as ComponentActivity).shouldShowRequestPermissionRationale(
                        Manifest.permission.RECORD_AUDIO)
                    )
            )
        }
    )

    LaunchedEffect(key1 = recordAudioLauncher){
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //top bar
            TopBar(
                backButtonText = stringResource(id = R.string.history),
                onBackClick = { onEvent(ChatEvent.GotoHistory) },
                onProfileClick = { onEvent(ChatEvent.GoToProfile) })
            //chat
            ChatView(
                question = state.question,
                answer = state.answer ?: "",
                generatingStatus = state.generatingStatus,
                onChangeQuestionText = { questionText -> onEvent(ChatEvent.OnTypeQuestion(questionText)) },
                onClose = {
                    onEvent(ChatEvent.CloseChatResult)
                          },
                onCopy = { copiedText ->
                    clipBoardManager.setText(
                        buildAnnotatedString {
                            append(copiedText)
                        }
                    )
                    Toast.makeText(
                        context,
                        context.getString(R.string.copied_to_clipboard),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
            // Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //speak button
                SpeakButton(
                    voiceToTextStatus = state.voiceToTextState,
                    onClick = { onEvent(ChatEvent.ToggleRecord) }
                )
                //go button
                GoButton(
                    onClick = { onEvent(ChatEvent.Go) },
                    enable = state.question.isNotEmpty()
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    CognifyTheme {
        PromptScreen(state = ChatState(), onEvent = {})
    }
}