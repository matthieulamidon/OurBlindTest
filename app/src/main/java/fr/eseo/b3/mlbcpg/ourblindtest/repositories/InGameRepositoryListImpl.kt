package fr.eseo.b3.mlbcpg.ourblindtest.repositories

import fr.eseo.b3.mlbcpg.ourblindtest.ListOfQuestion.ListOfQuestion
import fr.eseo.b3.mlbcpg.ourblindtest.model.QuestionBlindTest
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting

class InGameRepositoryListImpl: InGameRepository {
    private var listOfQuestion = mutableListOf<QuestionBlindTest>()
    private var setting = Setting()
    private var pseudo = ""
    private var score = 0
    private var currentQuestion = 0

    override suspend fun getAllQuestion(): List<QuestionBlindTest> {
        return listOfQuestion
    }

    override suspend fun getNextQuestion(): QuestionBlindTest {
        if (listOfQuestion.isEmpty()) {
            val testQuestion : QuestionBlindTest = QuestionBlindTest("testa", "testa", "testa", "testa", "testa")
            listOfQuestion.add(testQuestion)
            return listOfQuestion[0]
        }
        return listOfQuestion[currentQuestion]
    }

    override suspend fun setListOfQuestion(list: List<QuestionBlindTest>) {
        listOfQuestion = list.toMutableList()
    }

    override suspend fun generateListOfQuestion() {
        /*
            val testQuestion : QuestionBlindTest = QuestionBlindTest("testa", "testa", "testa", "testa", "testa")
            listOfQuestion.add(testQuestion)
            val testQuestion2 : QuestionBlindTest = QuestionBlindTest("testb", "testb", "testb", "testb", "testb")
                listOfQuestion.add(testQuestion2)
        */

        val generate = ListOfQuestion().generateQuestions(setting)
        listOfQuestion = generate.toMutableList()
        currentQuestion = 0
        score = 0
    }

    override suspend fun setPseudo(pseudoVoulu: String) {
        pseudo = pseudoVoulu
    }

    override suspend fun getPseudo(): String {
        return pseudo
    }

    override suspend fun nextQuestion() {
        currentQuestion++
    }

    override suspend fun getIdQuestion(): Int {
        return currentQuestion
    }


    override suspend fun setScore(scoreImp: Int) {
        score = scoreImp
    }

    override suspend fun getScore(): Int {
        return score
    }

    override suspend fun addScore(scoreImp: Int) {
        score += scoreImp
    }

    override suspend fun setSetting(settingImp: Setting) {
        setting = settingImp
    }

    override suspend fun getSetting(): Setting {
        return setting
    }

    override suspend fun resetGame() {
        score = 0
        currentQuestion = 0
    }

}