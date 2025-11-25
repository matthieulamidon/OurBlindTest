package fr.eseo.b3.mlbcpg.ourblindtest.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "score_table")
data class Score(
    @PrimaryKey var id: String = "",
    var name: String = "",
    var score: Int = 0,
    val creationDate: LocalDateTime = LocalDateTime.now()
)