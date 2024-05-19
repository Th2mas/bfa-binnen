package com.khlebovitch.bfa.binnen.cache.shared

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.khlebovitch.bfa.binnen.shared.cache.AppDatabase
import com.khlebovitch.bfa.binnen.shared.cache.Database
import com.khlebovitch.bfa.binnen.shared.cache.DatabaseDriverFactory
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

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
    fun `create chapter`() {
        val chapter = "Jachtbedienung und Jachtführung"

        val allChaptersBeforeCreation = database.getAllChapters()
        assertTrue(allChaptersBeforeCreation.isEmpty())

        database.createChapter(chapter)

        val allChaptersAfterCreation = database.getAllChapters()
        assertTrue(allChaptersAfterCreation.size == 1)
        assertEquals(allChaptersAfterCreation[0].name, chapter)
    }

    @Test
    fun `create question`() {
        val chapterId: Long = 2
        val questionText = "Was versteht man unter Luv?"

        val allQuestionsBeforeCreation = database.getAllQuestions()
        assertTrue(allQuestionsBeforeCreation.isEmpty())

        database.createQuestion(questionText, chapterId)

        val allQuestionsAfterCreation = database.getAllQuestions()
        assertTrue(allQuestionsAfterCreation.size == 1)

        val retrievedQuestion = allQuestionsAfterCreation[0]
        assertEquals(retrievedQuestion.text, questionText)
        assertEquals(retrievedQuestion.chapter_id, chapterId)
    }

    @Test
    fun `create text answer`() {
        val chapterId: Long = 2
        val questionText = "Was versteht man unter Luv?"
        database.createQuestion(questionText, chapterId)
        val questionId = database.getAllQuestions()[0].id
        val answerText = "Die dem Wind zugewandte Seite"

        assertTrue(database.getAllAnswers().isEmpty())
        assertTrue(database.getAllTextAnswers().isEmpty())

        database.createTextAnswer(questionId, answerText)

        // Assert Answer
        val allAnswersAfterCreation = database.getAllAnswers()
        assertTrue(allAnswersAfterCreation.size == 1)

        val retrievedAnswer = allAnswersAfterCreation[0]
        assertEquals(retrievedAnswer.question_id, questionId)

        // Assert TextAnswer
        val allTextAnswersAfterCreation = database.getAllTextAnswers()
        assertTrue(allTextAnswersAfterCreation.size == 1)

        val retrievedTextAnswer = allTextAnswersAfterCreation[0]
        assertEquals(retrievedTextAnswer.text, answerText)
    }

    @Test
    fun `create image answer`() {
        val chapterId: Long = 2
        val questionText = "Wie sieht ein Boot aus?"
        database.createQuestion(questionText, chapterId)
        val questionId = database.getAllQuestions()[0].id
        val answerImagePath = "images/boat.png"

        assertTrue(database.getAllAnswers().isEmpty())
        assertTrue(database.getAllImageAnswers().isEmpty())

        database.createImageAnswer(questionId, answerImagePath)

        // Assert Answer
        val allAnswersAfterCreation = database.getAllAnswers()
        assertTrue(allAnswersAfterCreation.size == 1)

        val retrievedAnswer = allAnswersAfterCreation[0]
        assertEquals(retrievedAnswer.question_id, questionId)

        // Assert ImageAnswer
        val allImageAnswersAfterCreation = database.getAllImageAnswers()
        assertTrue(allImageAnswersAfterCreation.size == 1)

        val retrievedImageAnswer = allImageAnswersAfterCreation[0]
        assertEquals(retrievedImageAnswer.image_path, answerImagePath)
    }

    @Test
    fun `create text QA pair`() {
        val chapterId: Long = 2
        val questionText = "Was versteht man unter Luv?"
        val answerText = "Die dem Wind zugewandte Seite"

        val allQuestionsBeforeCreate = database.getAllQuestions()
        val allAnswersBeforeCreate = database.getAllAnswers()
        assertTrue(allQuestionsBeforeCreate.isEmpty())
        assertTrue(allAnswersBeforeCreate.isEmpty())

        database.createQA(chapterId, questionText, answerText = answerText)

        val allQuestionsAfterCreate = database.getAllQuestions()
        val allAnswersAfterCreate = database.getAllAnswers()

        assertTrue(allQuestionsAfterCreate.size == 1)
        assertTrue(allAnswersAfterCreate.size == 1)

        val allQuestionTextAnswerPairs = database.getAllQuestionAnswerPairs()

        assertTrue(allQuestionTextAnswerPairs.size == 1)
        val retrievedQAPair = allQuestionTextAnswerPairs[0]
        assertEquals(retrievedQAPair.chapterId, chapterId)
        assertEquals(retrievedQAPair.questionText, questionText)
        assertEquals(retrievedQAPair.answerText, answerText)
    }
}
