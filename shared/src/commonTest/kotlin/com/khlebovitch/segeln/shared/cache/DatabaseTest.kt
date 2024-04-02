package com.khlebovitch.segeln.shared.cache

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseTest {
    private lateinit var database: Database
    private lateinit var mockDriverFactory: DatabaseDriverFactory

    private fun createInMemoryDatabase() {
        mockDriverFactory = mockk<DatabaseDriverFactory>()
        every { mockDriverFactory.createDriver() } answers {
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
                AppDatabase.Schema.create(this)
            }
        }
        database = Database(mockDriverFactory)
    }

    @BeforeTest
    fun beforeEach() {
        createInMemoryDatabase()
    }

    @Test
    fun `get all question titles`() {
        val questions: List<Question> = listOf(
            Question(0, "How much is the fish?", null),
            Question(1, "Is my name Dave?", null)
        )
        val expected = listOf("How much is the fish?", "Is my name Dave?")

        val allQuestionTitlesBeforeInsert = database.getAllQuestionTitles()
        assertEquals(allQuestionTitlesBeforeInsert, listOf())

        questions.forEach(database::insertQuestion)

        val allQuestionTitlesAfterInsert = database.getAllQuestionTitles()
        assertEquals(allQuestionTitlesAfterInsert, expected)
    }
}