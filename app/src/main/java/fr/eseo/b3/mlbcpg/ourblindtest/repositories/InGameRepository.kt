package fr.eseo.b3.mlbcpg.ourblindtest.repositories

import fr.eseo.b3.mlbcpg.ourblindtest.model.QuestionBlindTest
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting

interface InGameRepository {
    suspend fun getAllQuestion(): List<QuestionBlindTest>



    suspend fun getNextQuestion(): QuestionBlindTest

    suspend fun setListOfQuestion(list: List<QuestionBlindTest>)

    suspend fun generateListOfQuestion()

    suspend fun setPseudo(pseudoVoulu: String)

    suspend fun getPseudo(): String

    suspend fun nextQuestion() //id nb +1

    suspend fun getIdQuestion(): Int

    suspend fun setScore(scoreImp: Int)

    suspend fun getScore(): Int

    suspend fun addScore(scoreImp: Int)

    suspend fun setSetting(settingImp: Setting)

    suspend fun getSetting(): Setting

}