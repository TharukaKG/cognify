package com.tharuka.cognify.android.result.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.android.R
import com.tharuka.cognify.android.core.presentatiton.components.CloseButton
import com.tharuka.cognify.android.core.presentatiton.components.TextButton
import com.tharuka.cognify.android.result.presentation.components.ResultItem
import com.tharuka.cognify.result.presentation.ResultEvent
import com.tharuka.cognify.result.presentation.ResultState
import com.tharuka.cognify.result.presentation.ResultUiItem

@Composable
fun ResultScreen(
    state: ResultState,
    onEvent: (ResultEvent) -> Unit
) {

    val clipBoardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()){

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                CloseButton {
                    onEvent(ResultEvent.OnClose)
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(1f)
            ) {
                items(
                    items = state.results,
                    key = { it.id },
                ) { resultItem ->
                    ResultItem(
                        resultItem.answer,
                        resultItem.question
                    ) { copiedText ->
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
                }
            }
            if(state.continuable){
                Box(modifier = Modifier.fillMaxWidth().padding(8.dp), contentAlignment = Alignment.BottomEnd){
                    TextButton(text = stringResource(id = R.string.continue_to_chat)) {
                        onEvent(ResultEvent.OnContinue(state.chatId))
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewResultScreen() {
    CognifyTheme {
        ResultScreen(
            state = ResultState(
                results = listOf(
                    ResultUiItem(
                        id = "",
                        answer = "Hi, How can I help you today",
                        question = "Hello"
                    )
                ), continuable = true
            ), onEvent = {}
        )
    }
}