package com.tharuka.cognify.android.core.presentatiton.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.android.R

@Composable
fun CloseButton(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Sharp.Close,
            contentDescription = stringResource(id = R.string.close),
            tint = MaterialTheme.colors.onSecondary
        )
    }
}

@Preview
@Composable
fun PreviewCloseButton() {
    CognifyTheme {
        CloseButton {

        }
    }
}