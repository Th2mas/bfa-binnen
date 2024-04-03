package com.khlebovitch.bfa.binnen.android.screens.theory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khlebovitch.bfa.binnen.android.LearningOption
import com.khlebovitch.bfa.binnen.android.LearningOptionsNavigationBar
import com.khlebovitch.bfa.binnen.android.components.TextButtonList
import com.khlebovitch.bfa.binnen.android.components.ButtonListItem

@Composable
@Preview
fun CategoryScreen() {
    val categories: List<ButtonListItem> = listOf(
        "Jachtbedienung und Jachtführung",
        "Bootsbau",
        "Seemannschaft",
        "Gesetzeskunde",
        "Wettfahrtregeln Segeln",
        "Theorie des Segelns",
        "Wetterkunde",
        "Jachtgebräuche",
        "Verhalten bei Unglücksfällen"
    ).map { ButtonListItem(label = it) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            TextButtonList(categories)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LearningOptionsNavigationBar(selectedOption = LearningOption.THEORY)
        }
    }
}