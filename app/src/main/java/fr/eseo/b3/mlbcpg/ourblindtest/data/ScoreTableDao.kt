package fr.eseo.b3.mlbcpg.ourblindtest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.eseo.b3.mlbcpg.ourblindtest.model.Score


@Dao
interface ScoreTableDao {
    @Query("SELECT * FROM score_table")
    fun getAllScore(): List<Score>

    @Query("SELECT * FROM score_table WHERE id = :scoreId")
    suspend fun getNoteById(scoreId : String) : Score?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score : Score) : Long

    @Query("DELETE FROM score_table WHERE id = :scoreId")
    suspend fun deleteNoteById(scoreId : String)

    @Update
    suspend fun update(score: Score)

    @Delete
    suspend fun delete(score: Score)


}