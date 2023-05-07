package com.tharuka.cognify.android.core.presentatiton.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.padding(0.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button.copy(fontSize = 18.sp),
            color = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
fun PreviewOutlinedButton() {
    CognifyTheme {
        TextButton(text = "Continue") {}
    }
}