package fr.eseo.b3.mlbcpg.ourblindtest.repositories

import fr.eseo.b3.mlbcpg.ourblindtest.model.Score

interface OurBlindTestRepository {

    suspend fun getAllScore(): List<Score> //ok

    suspend fun addOrUpdateScore(score : Score) //ok

    suspend fun getScoreById(scoreId : String) : Score?//ok

    suspend fun deleteScore(score : Score)//ok
}


