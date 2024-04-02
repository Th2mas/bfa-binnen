package com.khlebovitch.segeln.shared.cache

/**
 * Contains all database related functions
 * This class is only accessible from within the multiplatform module (thus "internal")
 */
internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllQuestionTitles(): List<String> = dbQuery.selectQuestionNames().executeAsList()
    internal fun insertQuestion(question: Question) = dbQuery.createQuestion(question.text, question.tag_id)
}
