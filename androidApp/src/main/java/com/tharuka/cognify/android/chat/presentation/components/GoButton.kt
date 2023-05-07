package com.tharuka.cognify.android.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.android.R

@Composable
fun GoButton(
    onClick: () -> Unit,
    enable: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(48))
            .background(
                color = if (enable) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.primaryVariant
                }
            )
            .clickable(onClick = onClick, enabled = enable),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.go),
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewGoButton() {
    CognifyTheme {
        GoButton(onClick = {}, enable = false, modifier = Modifier)
    }
}