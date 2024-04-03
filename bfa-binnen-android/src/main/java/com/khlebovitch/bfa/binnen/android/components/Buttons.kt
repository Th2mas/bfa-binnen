package com.khlebovitch.bfa.binnen.android.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

const val next = "Weiter"
const val check = "Überprüfen"

@Composable
@Preview
private fun MaxWidthButton(text: String = "", onClick: () -> Unit = {}) = ElevatedButton(
    onClick = onClick,
    modifier = Modifier.fillMaxWidth()
) {
    Text(text)
}

@Composable
@Preview
fun NextButton(onClick: () -> Unit = {}) = MaxWidthButton(text = next, onClick = onClick)

@Composable
@Preview
fun CheckButton(onClick: () -> Unit = {}) = MaxWidthButton(text = check, onClick = onClick)
