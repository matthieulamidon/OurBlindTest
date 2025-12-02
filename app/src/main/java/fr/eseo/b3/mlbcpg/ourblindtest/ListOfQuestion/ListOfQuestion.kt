package fr.eseo.b3.mlbcpg.ourblindtest.ListOfQuestion

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.eseo.b3.mlbcpg.ourblindtest.model.QuestionBlindTest
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting
import kotlin.math.min

private data class SongData(
    val id: Int,
    val name: String,
    val author: String,
    val theme: String,
    val subTheme: String,
    val previewUrl: String?
)

class ListOfQuestion {
    companion object {
        const val QUESTIONS_JSON_DATA = """
        [
            {
                    "id": 1,
                    "name": "Enter Hallownest",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 2,
                    "name": "Dirtmouth",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 3,
                    "name": "Crossroads",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 4,
                    "name": "False Knight",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 5,
                    "name": "Greenpath",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 6,
                    "name": "Hornet",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 7,
                    "name": "Reflection",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 8,
                    "name": "Mantis Lords",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 9,
                    "name": "City of Tears",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 10,
                    "name": "Dung Defender",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 11,
                    "name": "Crystal Peak",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 12,
                    "name": "Fungal Wastes",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 13,
                    "name": "Decisive Battle",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 14,
                    "name": "Soul Sanctum",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 15,
                    "name": "Resting Grounds",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 16,
                    "name": "Queen's Gardens",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 17,
                    "name": "The White Lady",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 18,
                    "name": "Broken Vessel",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 19,
                    "name": "Kingdom's Edge",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 20,
                    "name": "Nosk",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 21,
                    "name": "Dream",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 22,
                    "name": "Dream Battle",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 23,
                    "name": "White Palace",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 24,
                    "name": "Sealed Vessel",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 25,
                    "name": "Radiance",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 26,
                    "name": "Hollow Knight",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 27,
                    "name": "Hive Knight",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 28,
                    "name": "Truth, Beauty and Hatred",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 29,
                    "name": "Nightmare Lantern (Interlude)",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 30,
                    "name": "The Grimm Troupe",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 31,
                    "name": "Nightmare King",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 32,
                    "name": "White Defender",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 33,
                    "name": "Dreamers",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 34,
                    "name": "Pale Court",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 35,
                    "name": "Gods & Glory",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 36,
                    "name": "Daughter of Hallownest",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 37,
                    "name": "Godhome",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 38,
                    "name": "Sisters of Battle",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 39,
                    "name": "Haunted Foes",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 40,
                    "name": "Furious Gods",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 41,
                    "name": "Pure Vessel",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Hollow Knight",
                    "previewUrl": null
                },
                {
                    "id": 42,
                    "name": "Wake Up, Get Up, Get Out There",
                    "author": "Lyn Inaizumi",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 43,
                    "name": "Life Will Change",
                    "author": "Lyn Inaizumi",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 44,
                    "name": "Last Surprise",
                    "author": "Lyn Inaizumi",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 45,
                    "name": "Beneath the Mask -instrumental version-",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 46,
                    "name": "Rivers In the Desert",
                    "author": "Lyn Inaizumi",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 47,
                    "name": "Full Moon Full Life",
                    "author": "Lotus Juice / Azumi Takahashi / ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 48,
                    "name": "Mass Destruction -Reload-",
                    "author": "Lotus Juice / Azumi Takahashi / ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 49,
                    "name": "When The Moon's Reaching Out Stars -Reload-",
                    "author": "Azumi Takahashi / ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 50,
                    "name": "Pursuing My True Self",
                    "author": "Shihoko Hirata / ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 51,
                    "name": "I'll Face Myself -Battle-",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 52,
                    "name": "Never More",
                    "author": "平田志穂子",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 53,
                    "name": "Aria of the Soul (P3R ver.)",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 54,
                    "name": "Wiping All Out",
                    "author": "Shoji Meguro",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 55,
                    "name": "Time To Make History",
                    "author": "Shoji Meguro",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 56,
                    "name": "Heaven",
                    "author": "Shoji Meguro",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 57,
                    "name": "Heartbeat, Heartbreak",
                    "author": "Shoji Meguro",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 58,
                    "name": "Deep Breath Deep Breath",
                    "author": "Shoji Meguro",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 59,
                    "name": "The Fog",
                    "author": "Shoji Meguro",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 60,
                    "name": "Our Beginning",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 61,
                    "name": "Regret",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 62,
                    "name": "Will Power",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 63,
                    "name": "Daredevil",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 64,
                    "name": "Throw Away Your Mask",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                }
        ]
        """
    }

    fun generateQuestions(settings: Setting): List<QuestionBlindTest> {
        val gson = Gson()

        val itemType = object : TypeToken<List<SongData>>() {}.type
        val allSongs: List<SongData> = gson.fromJson(QUESTIONS_JSON_DATA, itemType)

        Log.d("allSongs", allSongs.toString())


        val filteredSongs = allSongs /*.filter { song ->
            val noThemeFilter = settings.theme == "null" || settings.theme == ""


            val noSubThemeFilter = settings.sbTheme == "null" || settings.sbTheme == ""

            val themeMatches = noThemeFilter || song.theme == settings.theme
            val subThemeMatches = noSubThemeFilter || song.subTheme == settings.sbTheme

            themeMatches && subThemeMatches
        }*/


        val shuffledSongs = filteredSongs.shuffled()
        val limit = min(settings.nb, shuffledSongs.size)
        val selectedSongs = shuffledSongs.take(limit)

        val questions = selectedSongs.map { correctSong ->

            // j'adore la manipulation de liste qu'elle enfer
            // et oui c'est gemini qui ma aider pour ce code car je suis TEUBE
            val wrongAnswers = allSongs
                .filter { it.name != correctSong.name }
                .shuffled()
                .take(3)
                .map { it.name }

            val wrong1 = wrongAnswers.getOrElse(0) { "ANY Radiance" }
            val wrong2 = wrongAnswers.getOrElse(1) { "Any Radiance V2" }
            val wrong3 = wrongAnswers.getOrElse(2) { "Dies Ire, Dies Illa" }

            // On crée votre objet final
            QuestionBlindTest(
                title = correctSong.name,
                author = correctSong.author,
                falseChose1 = wrong1,
                falseChose2 = wrong2,
                falseChose3 = wrong3
            )
        }

        Log.d("questions", questions.toString())
        return questions
    }
}