package com.khlebovitch.bfa.binnen.shared.cache

/**
 * Contains all database related functions
 * This class is only accessible from within the multiplatform module (thus "internal")
 */
internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllChapters(): List<Chapter> = dbQuery.selectChapters().executeAsList()

    internal fun createChapter(name: String) = dbQuery.createChapter(name)

    internal fun getAllQuestions(): List<Question> = dbQuery.selectQuestions().executeAsList()
    internal fun createQuestion(questionText: String, chapterId: Long, imagePath: String? = null) =
        dbQuery.createQuestion(chapterId, questionText, imagePath)

    internal fun createTextAnswer(questionId: Long, answerText: String) {
        dbQuery.createAnswer(questionId)
        val answer = dbQuery.findAnswerByQuestionId(questionId).executeAsOne()
        dbQuery.createTextAnswer(answer.id, answerText)
    }

    internal fun getAllAnswers(): List<Answer> = dbQuery.selectAnswers().executeAsList()
    internal fun getAllTextAnswers(): List<TextAnswer> = dbQuery.selectTextAnswers().executeAsList()

    internal fun getAllImageAnswers(): List<ImageAnswer> = dbQuery.selectImageAnswers().executeAsList()

    internal fun createImageAnswer(questionId: Long, answerImagePath: String) {
        dbQuery.createAnswer(questionId)
        val answer = dbQuery.findAnswerByQuestionId(questionId).executeAsOne()
        dbQuery.createImageAnswer(answer.id, answerImagePath)
    }

    internal fun createQA(chapterId: Long, questionText: String, answerText: String? = null, answerImagePath: String? = null) {
        createQuestion(questionText, chapterId)
        val question = dbQuery.findQuestionByText(questionText).executeAsOne()

        dbQuery.createAnswer(question.id)
        val answer = dbQuery.findAnswerByQuestionId(question.id).executeAsOne()

        if (answerText != null) {
            dbQuery.createTextAnswer(answer.id, answerText)
        }

        if (answerImagePath != null) {
            dbQuery.createImageAnswer(answer.id, answerImagePath)
        }
    }

    internal fun getAllQuestionAnswerPairs() = dbQuery.selectQAs().executeAsList()
}
