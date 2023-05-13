package com.tharuka.cognify.android.chat.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tharuka.cognify.android.R
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme

@Composable
fun ChatTextField(
    text: String,
    onTextChange: (String) -> Unit,
    onClearText: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        //clear text
        if (isFocused && text.isNotEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        focusManager.clearFocus()
                        onClearText()
                    },
                    imageVector = Icons.Sharp.Clear,
                    contentDescription = stringResource(id = R.string.clear_text)
                )
            }
        }
        Box(
            modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart
        ) {

            //text field
            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
                modifier = modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                textStyle = MaterialTheme.typography.body1,
            )

            if (!isFocused && text.isEmpty()) {
                Text(
                    text = stringResource(
                        id = R.string.ask_anything
                    ),
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSecondary)
                )
            }

        }
    }

}

@Preview
@Composable
fun Preview() {
    CognifyTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ChatTextField(
                text = "Ask Anything",
                onTextChange = {},
                onClearText = {},
                modifier = Modifier
            )
        }
    }
}