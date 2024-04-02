package com.khlebovitch.bfa.binnen.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class LearningOption(val title: String, val description: String)

@Composable
@Preview
fun LearningOptionsScreen(onContinueClicked: () -> Unit = {}) {
    val header = "BFA Binnen Kurs"
    val learningOptions: List<LearningOption> = listOf(
        LearningOption(title = "Theorie", description = "Gehe den Stoff durch"),
        LearningOption(
            title = "Quizze",
            description = "Stelle deine Theoriekenntnisse auf die Probe"
        ),
        LearningOption(
            title = "Prüfung",
            description = "Probiere eine Prüfung und siehe, ob du bestehen würdest"
        )
    )
    val next = "Weiter"
    var selectedLearningOption by remember { mutableStateOf(learningOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            header,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        HorizontalDivider()

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            learningOptions.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .clickable { selectedLearningOption = option }
                        .background(if (selectedLearningOption == option) Color.LightGray else Color.Transparent)
                ) {
                    Column {
                        Text(option.title, style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(option.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        HorizontalDivider()

        Button(onClick = onContinueClicked, modifier = Modifier.align(Alignment.End)) {
            Text(next)
        }
    }
}