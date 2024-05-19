package com.khlebovitch.bfa.binnen.android.screens.theory

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SectionHeader() {
    val sectionNumber = 1
    val sectionName = "Jachtbedienung und -führung"
    val theoryLink = "/link-to-section-1-theory"

    TopAppBar(title = { Text("Sektion" + 1) })
}