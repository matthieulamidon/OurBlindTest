package fr.eseo.b3.mlbcpg.ourblindtest.repositories

import android.app.Application
import android.content.Context
import fr.eseo.b3.mlbcpg.ourblindtest.data.OurBlindTestDatabaseProvider
import fr.eseo.b3.mlbcpg.ourblindtest.model.Score

class OurBlindTestRepositoriesRoomImp(application: Application) : OurBlindTestRepository {

    private val db = OurBlindTestDatabaseProvider.getDatabase(application)
    private val scoreDao = db.ScoreTableDao()

    private val scoreLimit = 5

    override suspend fun addOrUpdateScore(score: Score) {
        scoreDao.insert(score)

        val allScores = scoreDao.getAllScore()

        if (allScores.size > scoreLimit) { // s'il y a plus de 5 scores, supprime le moins bon
            val scoresToDelete = allScores.subList(scoreLimit, allScores.size)

            scoresToDelete.forEach { scoreToDelete ->
                scoreDao.delete(scoreToDelete)
            }
        }
    }

    override suspend fun getAllScore(): List<Score> {
        return scoreDao.getAllScore()
    }

    override suspend fun getScoreById(scoreId: Int): Score? {
        return scoreDao.getNoteById(scoreId)
    }

    override suspend fun deleteScore(score: Score) {
        return scoreDao.delete(score)
    }
}