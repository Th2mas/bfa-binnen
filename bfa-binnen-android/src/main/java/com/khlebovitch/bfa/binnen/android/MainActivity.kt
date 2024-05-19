package com.khlebovitch.bfa.binnen.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.khlebovitch.bfa.binnen.BfaBinnenSDK
import com.khlebovitch.bfa.binnen.shared.cache.DatabaseDriverFactory

class MainActivity : ComponentActivity() {

    private lateinit var sdk: BfaBinnenSDK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sdk = BfaBinnenSDK(DatabaseDriverFactory(this))
        sdk.initializeDatabase()
        val chapters = sdk.getAllTheoryQuestionAnswers()

        setContent {
            BfaBinnenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    chapters.forEach { entry ->
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            item {
                                Text(
                                    text = entry.key,
                                    fontSize = 24.sp
                                )
                            }
                            items(entry.value) {
                                ListItem(
                                    headlineContent = {
                                        Text(it.questionText)
                                    },
                                    supportingContent = {
                                        Text(it.answerText ?: "")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
