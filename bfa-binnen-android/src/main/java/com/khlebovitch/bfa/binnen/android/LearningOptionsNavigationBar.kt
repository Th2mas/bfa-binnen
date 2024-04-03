package com.khlebovitch.bfa.binnen.android

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

data class NavigationOption(val option: LearningOption, val icon: ImageVector)

enum class LearningOption(val value: String) {
    THEORY("Theorie"),
    QUIZ("Quiz"),
    EXAM("Prüfung"),
    SETTINGS("Einstellungen")
}

val navigationOptions = listOf(
    NavigationOption(option = LearningOption.THEORY, icon = Icons.Filled.AutoStories),
    NavigationOption(option = LearningOption.QUIZ, icon = Icons.Filled.Quiz),
    NavigationOption(option = LearningOption.EXAM, icon = Icons.AutoMirrored.Filled.Assignment),
    NavigationOption(option = LearningOption.SETTINGS, icon = Icons.Filled.Settings)
)

@Composable
@Preview
fun LearningOptionsNavigationBar(selectedOption: LearningOption = LearningOption.THEORY) {
    NavigationBar {
        navigationOptions.forEach { navigationOption ->
            NavigationBarItem(
                selected = navigationOption.option == selectedOption,
                onClick = { TODO() },
                icon = { Icon(navigationOption.icon, contentDescription = navigationOption.option.value) },
                label = { Text(navigationOption.option.value) }
            )
        }
    }
}