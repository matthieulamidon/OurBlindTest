package fr.eseo.b3.mlbcpg.ourblindtest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.eseo.b3.mlbcpg.ourblindtest.model.Score

@Database(
    entities = [Score::class],
    version = 2
)
@TypeConverters(OurBlindTestConverters::class)
abstract class OurBlindTestDatabase  : RoomDatabase() {

    abstract fun ScoreTableDao(): ScoreTableDao

}
