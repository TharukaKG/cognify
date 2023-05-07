package com.tharuka.cognify.android.chat.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tharuka.cognify.android.R
import com.tharuka.cognify.android.core.presentatiton.components.CloseButton

@Composable
fun ChatResult(
    question: String,
    answer: String,
    onClose: () -> Unit,
    onCopy: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        //close button
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            CloseButton(onClick = onClose)
        }
        //question
//        AnimatedVisibility(visible = question.isNotEmpty()) {
        Text(
            text = question,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary
        )
        //divider
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = MaterialTheme.colors.onBackground
        )
        //answer
        AnimatedVisibility(visible = answer.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.onBackground)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = answer,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(8.dp)
                    )
                    //copy button
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(onClick = { onCopy(answer) }) {
                            Icon(
                                imageVector = Icons.Rounded.ContentCopy,
                                contentDescription = stringResource(id = R.string.copy),
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
        }
        AnimatedVisibility(visible = answer.isEmpty()) {
            GeneratingAnimation()
        }
    }
}