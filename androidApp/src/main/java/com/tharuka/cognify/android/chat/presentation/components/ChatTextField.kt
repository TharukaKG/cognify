package com.tharuka.cognify.android.chat.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme

@Composable
fun ChatTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var isFocused by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxWidth().padding(12.dp), contentAlignment = Alignment.CenterStart){
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 24.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            textStyle = MaterialTheme.typography.body1,
        )

        if (!isFocused && text.isEmpty()) {
            Text(
                text = stringResource(
                    id = com.tharuka.cognify.android.R.string.ask_anything
                ),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSecondary)
            )
        }
    }
}

@Preview
@Composable
fun Preview(){
    CognifyTheme{
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ChatTextField(text = "Ask Anything",  onTextChange = {}, modifier = Modifier)
        }
    }
}