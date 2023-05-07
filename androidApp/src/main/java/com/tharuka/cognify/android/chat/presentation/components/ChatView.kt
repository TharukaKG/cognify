package com.tharuka.cognify.android.chat.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onClearQuestion: () -> Unit,
    onClose: () -> Unit,
    onCopy: (String) -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Grayish)
            .animateContentSize()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        when (generatingStatus) {
            GeneratingStatus.IDLE -> {
                ChatTextField(
                    text = question,
                    onTextChange = onChangeQuestionText,
                    onClearText = onClearQuestion
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
            onChangeQuestionText = {},
            onClearQuestion = {}
        )
    }
}