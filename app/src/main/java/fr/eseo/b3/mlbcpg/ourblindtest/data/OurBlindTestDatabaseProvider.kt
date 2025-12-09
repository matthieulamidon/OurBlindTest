package fr.eseo.b3.mlbcpg.ourblindtest.data

import android.content.Context
import androidx.room.Room.databaseBuilder

object OurBlindTestDatabaseProvider {
    @Volatile
    private var INSTANCE : OurBlindTestDatabase? = null

    fun getDatabase(context : Context) : OurBlindTestDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = databaseBuilder(
                context = context.applicationContext,
                klass = OurBlindTestDatabase::class.java,
                name  = "our_blind_test_database"
            ).fallbackToDestructiveMigration() // version pas très propre mais plus rapide pour mettre à jour la structure de la database
            .build()
            INSTANCE = instance
            instance
        }
    }
}