package com.khlebovitch.bfa.binnen.android.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class ButtonListItem(val label: String, val icon: ImageVector? = null, val onClick: () -> Unit = {})
@Composable
fun TextButtonList(buttonListItems: List<ButtonListItem> = listOf()) {
    var selectedButtonIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        buttonListItems.forEachIndexed { index, buttonListItem ->
            ListItem(
                headlineContent = {
                    TextButton(onClick = {
                        selectedButtonIndex = index
                        buttonListItem.onClick()
                    }) {
                        Text(buttonListItem.label)
                    }
                },
                leadingContent = {
                    if (buttonListItem.icon != null) {
                        Icon(buttonListItem.icon, contentDescription = "")
                    }
                }
            )
        }
    }
}