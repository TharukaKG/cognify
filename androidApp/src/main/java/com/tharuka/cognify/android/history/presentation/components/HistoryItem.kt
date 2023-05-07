package com.tharuka.cognify.android.history.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.history.presentation.HistoryUiItem

@Composable
fun HistoryItem(
    historyUiItem: HistoryUiItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colors.secondary)
            .clickable { onClick() }
    ) {
        Text(
            text = historyUiItem.text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun HistoryItemPreview() {
    CognifyTheme {
        HistoryItem(historyUiItem = HistoryUiItem("", "Hi, How can I help you today")) {

        }
    }
}