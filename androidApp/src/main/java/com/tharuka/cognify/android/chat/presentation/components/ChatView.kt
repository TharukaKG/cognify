package com.tharuka.cognify.android.chat.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.android.core.presentatiton.theme.Grayish
import com.tharuka.cognify.chat.presentation.GeneratingStatus

@Composable
fun ChatView(
    question: String,
    answer: String,
    generatingStatus: GeneratingStatus,
    modifier: Modifier = Modifier,
    onChangeQuestionText: (String) -> Unit,
    onClose: () -> Unit,
    onCopy: (String) -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Grayish)
            .animateContentSize()
    ) {
        when (generatingStatus) {
            GeneratingStatus.IDLE -> {
                ChatTextField(
                    text = question,
                    onTextChange = onChangeQuestionText
                )
            }
            else -> {
                ChatResult(
                    question = question,
                    answer = answer,
                    onClose = onClose,
                    onCopy = onCopy
                )
            }
        }
    }
}

@Preview
@Composable
fun ChatResultPreview() {
    CognifyTheme {
        ChatView(
            question = "What is quantum physics",
            answer = "Quantum physics is some thing that I don't aware of",
            generatingStatus = GeneratingStatus.IDLE,
            onClose = {},
            onCopy = {},
            onChangeQuestionText = {}
        )
    }
}