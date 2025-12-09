package fr.eseo.b3.mlbcpg.ourblindtest.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "score_table")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var score: Int,
    val creationDate: LocalDateTime = LocalDateTime.now()
)