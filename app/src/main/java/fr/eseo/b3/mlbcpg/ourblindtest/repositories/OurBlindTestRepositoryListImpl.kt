package fr.eseo.b3.mlbcpg.ourblindtest.repositories

import fr.eseo.b3.mlbcpg.ourblindtest.model.Score


class OurBlindTestRepositoryListImpl : OurBlindTestRepository {

    private val scoreTable = mutableListOf<Score>()

    override suspend fun getAllScore(): List<Score> {
        return scoreTable.toList()
    }

    override suspend fun addOrUpdateScore(note: Score) {
        val existingIndex = scoreTable.indexOfFirst { it.id == note.id }
        if(existingIndex != -1) {
            scoreTable[existingIndex] = note
        }
        else {
            scoreTable.add(note)
        }
    }

    override suspend fun getScoreById(scoreId: Int): Score? {
        return scoreTable.find{it.id == scoreId}
    }

    override suspend fun deleteScore(score: Score) {
        scoreTable.removeIf { it.id == score.id }
    }
}
