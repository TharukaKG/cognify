package com.tharuka.cognify.android.core.presentatiton.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tharuka.cognify.android.core.presentatiton.theme.CognifyTheme
import com.tharuka.cognify.android.R
import com.tharuka.cognify.android.core.presentatiton.theme.Grayish

@Composable
fun TopBar(
    backButtonText: String,
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextBackButton(text = backButtonText, onClick = onBackClick)
        IconButton(onClick = onProfileClick) {
            Icon(
                modifier = Modifier.clip(CircleShape).background(Grayish).padding(4.dp),
                imageVector = Icons.Rounded.Person,
                contentDescription = stringResource(id = R.string.profile),
                tint = MaterialTheme.colors.primary
            )
        }
    }

}

@Composable
fun TextBackButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(onClick = onClick, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_back_arrow),
                contentDescription = text,
                tint = MaterialTheme.colors.onSecondary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.button.copy(
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    CognifyTheme {
        TopBar(backButtonText = "History", onBackClick = {}, onProfileClick = {})
    }
}