package com.tharuka.cognify.android.history.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.android.R
import com.tharuka.cognify.android.core.presentatiton.components.TextBackButton
import com.tharuka.cognify.android.core.presentatiton.components.TopBar
import com.tharuka.cognify.android.history.presentation.components.HistoryItem
import com.tharuka.cognify.history.presentation.HistoryEvent
import com.tharuka.cognify.history.presentation.HistoryState

@Composable
fun HistoryScreen(
    state: HistoryState,
    onEvent: (HistoryEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            //chat button
            item {
                TopBar(
                    backButtonText = stringResource(id = R.string.chat),
                    onBackClick = { onEvent(HistoryEvent.GotoChat) },
                    onProfileClick = {}
                )
            }
            //history items
            items(items = state.historyItems, key = { it.id }) { historyUiItem ->
                HistoryItem(historyUiItem) {
                    onEvent(HistoryEvent.SelectItem(historyUiItem.id))
                }
            }
        }
        //new chat
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(
                onClick = { onEvent(HistoryEvent.NewChat) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.new_chat),
                    style = MaterialTheme.typography.button.copy(fontSize = 16.sp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    CognifyTheme {
        HistoryScreen(state = HistoryState(), onEvent = {})
    }
}