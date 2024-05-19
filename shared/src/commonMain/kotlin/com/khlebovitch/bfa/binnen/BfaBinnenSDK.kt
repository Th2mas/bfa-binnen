package com.khlebovitch.bfa.binnen

import com.khlebovitch.bfa.binnen.shared.cache.Database
import com.khlebovitch.bfa.binnen.shared.cache.DatabaseDriverFactory
import com.khlebovitch.bfa.binnen.shared.cache.QA
import com.khlebovitch.bfa.binnen.shared.cache.initDatabase

class BfaBinnenSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)

    fun initializeDatabase() = initDatabase(database)
    fun getAllTheoryQuestionAnswers(): Map<String, List<QA>> {
        val chapters = database.getAllChapters().associateBy { it.id }
        val qas = database.getAllQuestionAnswerPairs().groupBy { it.chapterId }

        return qas.mapKeys { chapters[it.key]?.name ?: throw IllegalStateException("No chapter with ID ${it.key}") }
            .mapValues { entry -> entry.value.map { QA(questionText = it.questionText, answerText = it.answerText, answerImagePath = it.answerImagePath) } }
    }
}
