package com.khlebovitch.bfa.binnen.shared.cache

private val CHAPTERS = listOf(
    "Jachtbedienung und Jachtführung",
    "Bootsbau",
    "Seemannschaft",
    "Gesetzeskunde",
    "Wettfahrtregeln Segeln",
    "Theorie des Segelns",
    "Wetterkunde",
    "Jachtgebräuche",
    "Verhalten bei Unglücksfällen"
)

private val CHAPTER1_QAS = listOf(
    QA(
        "Was versteht man unter Luv?",
        "Die dem Wind zugewandte Seite."
    ),
    QA(
        "Was versteht man unter Lee?",
        "Die dem Wind abgewandte Seite."
    ),
    QA(
        "Was versteht man unter Anluven?",
        "Höher an den Wind drehen, Pinne zum Baum."
    ),
    QA(
        "Was versteht man unter Abfallen?",
        "Vom Wind wegdrehen, Pinne vom Baum."
    ),
    QA(
        "Was versteht man unter Fieren / Schricken?",
        "Das Nachlassen eines Leinenendes."
    ),
    QA(
        "Was versteht man unter Dichtholen / Anholen / Heißen?",
        "Das Anziehen an einem Leinenende."
    ),
    QA(
        "Was versteht man unter einer Wende / über Stag gehen?",
        "Eine Kursänderung mit dem Bug durch den Wind."
    ),
    QA(
        "Wie kann man das Wenden unterstützen?",
        "Indem man das Vorsegel back hält."
    ),
    QA(
        "Was versteht man unter Backhalten eines Segels?",
        "Das Segel nach Luv drücken, damit es den Wind von der falschen Seite bekommt."
    )
)

internal fun initDatabase(database: Database) {
    database.getAllQuestionAnswerPairs().forEach(::println)

    if (database.getAllChapters().isEmpty()) {
        println("Creating chapters")
        CHAPTERS.forEach(database::createChapter)
    }

    if (database.getAllQuestions().isEmpty()) {
        println("Creating questions")
        CHAPTER1_QAS.forEach {
            database.createQA(1, questionText = it.questionText, answerText = it.answerText)
        }
    }
}
