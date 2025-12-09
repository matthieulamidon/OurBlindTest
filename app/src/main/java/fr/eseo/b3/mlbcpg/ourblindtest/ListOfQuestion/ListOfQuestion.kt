package fr.eseo.b3.mlbcpg.ourblindtest.ListOfQuestion

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.eseo.b3.mlbcpg.ourblindtest.model.QuestionBlindTest
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting
import fr.eseo.b3.mlbcpg.ourblindtest.model.Theme
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
                    "name": "Ce rêve bleu",
                    "author": "Karine Costa",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 28,
                    "name": "Un Poco Loco",
                    "author": "Andrea Santamaria",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 29,
                    "name": "Je Suis Ton Ami",
                    "author": "CharlElie Couture",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 30,
                    "name": "Le bleu lumière",
                    "author": "Cerise Calixte",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 31,
                    "name": "Nuits d'Arabie",
                    "author": "Bernard Alane",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 32,
                    "name": "De Zéro en Héros",
                    "author": "Mimi Felixine",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 33,
                    "name": "Le Festin",
                    "author": "Camille",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 34,
                    "name": "Un jour, mon prince viendra",
                    "author": "Rachel Pignot",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 35,
                    "name": "Joyeux non-anniversaire",
                    "author": "Cast of Alice in Wonderland",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 36,
                    "name": "Les Cloches de Notre-Dame",
                    "author": "Bernard Alane",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 37,
                    "name": "Monsters, Inc.",
                    "author": "Randy Newman",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 38,
                    "name": "Gospel",
                    "author": "MarchFourth",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 39,
                    "name": "Rien qu'un Jour",
                    "author": "Francis Lalanne",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 40,
                    "name": "On My Way",
                    "author": "Phil Collins",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 41,
                    "name": "It's A Small World (Disneyland Paris version)",
                    "author": "Cast - Disneyland Paris",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
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
                    "author": "Azumi Takahashi",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 50,
                    "name": "Pursuing My True Self",
                    "author": "平田志穂子",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 51,
                    "name": "I'll Face Myself -Battle-",
                    "author": "アトラスサウンドチーム",
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
                    "name": "Your Affection",
                    "author": "平田志穂子",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 55,
                    "name": "Time To Make History",
                    "author": "平田志穂子",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 56,
                    "name": "Heaven",
                    "author": "平田志穂子",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 57,
                    "name": "Heartbeat, Heartbreak",
                    "author": "平田志穂子",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 58,
                    "name": "Color Your Night",
                    "author": "Lotus Juice",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 59,
                    "name": "Reach Out To The Truth",
                    "author": "平田志穂子",
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
                    "name": "Layer Cake",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 64,
                    "name": "Throw Away Your Mask",
                    "author": "Lyn Inaizumi",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 65,
                    "name": "Libérée, délivrée",
                    "author": "Anaïs Delva",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 66,
                    "name": "Ce rêve bleu",
                    "author": "Karine Costa, Paolo Domingo",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 67,
                    "name": "L'Histoire De La Vie",
                    "author": "Debbie Davis",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 68,
                    "name": "You're Welcome",
                    "author": "Dwayne Johnson",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 69,
                    "name": "Sous l'océan",
                    "author": "Henri Salvador",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 70,
                    "name": "Je Voudrais Déjà Être Roi",
                    "author": "Dimitri Rougeul, Michel Prudhomme",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 71,
                    "name": "Ne parlons pas de Bruno",
                    "author": "Sharon Laloum, Julian Ortiz Cardona",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 72,
                    "name": "Où est la vraie vie ?",
                    "author": "Maeva Méline",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 73,
                    "name": "Hakuna Matata",
                    "author": "Dimitri Rougeul, Emmanuel Curtil, Jean-Philippe Puymartin",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 74,
                    "name": "Il En Faut Peu Pour Être Heureux",
                    "author": "Jean Stout, Pascal Bressy",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 75,
                    "name": "Partir là-bas",
                    "author": "Claire Guyot",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 76,
                    "name": "L'air Du Vent",
                    "author": "Laura Mayne",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 77,
                    "name": "Femme Like U",
                    "author": "K-Maro",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 78,
                    "name": "La Boulette (Génération Nan Nan)",
                    "author": "Diam's",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 79,
                    "name": "Temperature",
                    "author": "Sean Paul",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 80,
                    "name": "Umbrella",
                    "author": "Rihanna, JAY Z",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 81,
                    "name": "Hips Don't Lie (feat. Wyclef Jean)",
                    "author": "Shakira, Wyclef Jean",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 82,
                    "name": "En apesanteur",
                    "author": "Calogero",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 83,
                    "name": "Dernière danse",
                    "author": "Kyo",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 84,
                    "name": "Crazy In Love (feat. JAY-Z)",
                    "author": "Beyoncé, JAY Z",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 85,
                    "name": "Toxic",
                    "author": "Britney Spears",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 86,
                    "name": "J'ai demandé à la lune",
                    "author": "Indochine",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 87,
                    "name": "Love Don't Let Me Go",
                    "author": "David Guetta",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 88,
                    "name": "Moi... Lolita",
                    "author": "Alizée",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 89,
                    "name": "I Gotta Feeling",
                    "author": "Black Eyed Peas",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 90,
                    "name": "Poker Face",
                    "author": "Lady Gaga",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 91,
                    "name": "Single Ladies (Put a Ring on It)",
                    "author": "Beyoncé",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 92,
                    "name": "Hot N Cold",
                    "author": "Katy Perry",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 93,
                    "name": "Get the Party Started",
                    "author": "P!nk",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 94,
                    "name": "Mr. Brightside",
                    "author": "The Killers",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 95,
                    "name": "Veridis Quo",
                    "author": "Daft Punk",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 96,
                    "name": "Hey Ya!",
                    "author": "Outkast",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 97,
                    "name": "Rehab",
                    "author": "Amy Winehouse",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 98,
                    "name": "Chasing Cars",
                    "author": "Snow Patrol",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 99,
                    "name": "Shut Up",
                    "author": "Black Eyed Peas",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 100,
                    "name": "Ma philosophie",
                    "author": "Amel Bent",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 101,
                    "name": "Le vent nous portera",
                    "author": "Noir Désir",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 102,
                    "name": "J't'emmène au vent",
                    "author": "Louise Attaque",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 103,
                    "name": "Alive",
                    "author": "Empire of the Sun",
                    "theme": "ANNEES_2000",
                    "subTheme": null,
                    "previewUrl": null
                },
                {
                    "id": 104,
                    "name": "Signs Of Love",
                    "author": "平田志穂子",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 105,
                    "name": "Like a dream come true",
                    "author": "アトラスサウンドチーム",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 106,
                    "name": "SMILE",
                    "author": "アトラスサウンドチーム",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 107,
                    "name": "specialist",
                    "author": "アトラスサウンドチーム",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                 {
                    "id": 108,
                    "name": "Period",
                    "author": "アトラスサウンドチーム",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 109,
                    "name": "The Genesis",
                    "author": "アトラスサウンドチーム",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 110,
                    "name": "Game",
                    "author": "アトラスサウンドチーム",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 111,
                    "name": "Take Over",
                    "author": "Lyn Inaizumi",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 112,
                    "name": "Ideal and the Real",
                    "author": "ATLUS Sound Team",
                    "theme": "Video Game",
                    "subTheme": "Persona",
                    "previewUrl": null
                },
                {
                    "id": 113,
                    "name": "Enter Pharloom",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 114,
                    "name": "Moss Grotto",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 115,
                    "name": "Strive",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 116,
                    "name": "Bone Bottom",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 117,
                    "name": "The Marrow",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 118,
                    "name": "Bell Beast",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 119,
                    "name": "Repose",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 120,
                    "name": "Deep Docks",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 121,
                    "name": "Lace",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 122,
                    "name": "Far Fields",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 123,
                    "name": "Fourth Chorus",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 124,
                    "name": "Greymoor",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 125,
                    "name": "Incisive Battle",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 126,
                    "name": "Bellhart",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 127,
                    "name": "Widow",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 128,
                    "name": "Shellwood",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 129,
                    "name": "Sister Splinter",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 130,
                    "name": "Hunter's Trail",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 131,
                    "name": "Sinner's Road",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 132,
                    "name": "Cut Through",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                 {
                    "id": 133,
                    "name": "Bilewater",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 134,
                    "name": "The Mist",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 135,
                    "name": "Phantom",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 136,
                    "name": "The Slab",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 137,
                    "name": "Red Maiden",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 138,
                    "name": "Mount Fay",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 139,
                    "name": "Blasted Steps",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 140,
                    "name": "Last Judge",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 141,
                    "name": "Underworks",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 142,
                    "name": "Choral Chambers",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 143,
                    "name": "Songclave",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 144,
                    "name": "Cogwork Dancers",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 145,
                    "name": "Cogwork Core",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 146,
                    "name": "Whispering Vaults",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 147,
                    "name": "Trobbio",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 148,
                    "name": "High Halls",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 149,
                    "name": "The Choir",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 150,
                    "name": "Awakening",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 151,
                    "name": "Dark Descent",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 152,
                    "name": "Reprieve",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 153,
                    "name": "Nyleth",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 154,
                    "name": "Silksong",
                    "author": "Christopher Larkin",
                    "theme": "Video Game",
                    "subTheme": "Silksong",
                    "previewUrl": null
                },
                {
                    "id": 155,
                    "name": "The Legend of Zelda Theme",
                    "author": "Video Games Players",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 156,
                    "name": "Gerudo Valley",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 157,
                    "name": "Great Fairy Fountain",
                    "author": "Koji Kondo",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 158,
                    "name": "Song of Storms",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 159,
                    "name": "Breath of the Wild",
                    "author": "Rozen",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 160,
                    "name": "Dragon Roost Island",
                    "author": "PPF",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 161,
                    "name": "Zelda's Lullaby",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 162,
                    "name": "Lost Woods",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 163,
                    "name": "Song of Healing",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 164,
                    "name": "Bolero Of Fire",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 165,
                    "name": "Je Veux Savoir",
                    "author": "Phill Collins",
                    "theme": "Dessins Animés",
                    "subTheme": "Disney",
                    "previewUrl": null
                },
                {
                    "id": 166,
                    "name": "Ballad of the Goddess",
                    "author": "Rozen",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 167,
                    "name": "Kotake Koume Theme",
                    "author": "The Generous Margins",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 168,
                    "name": "Kakariko Village",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 169,
                    "name": "The Legend Of Zelda: The Windwaker - The Great Sea",
                    "author": "Ben Morfitt (SquidPhysics)",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 170,
                    "name": "Tarrey Town",
                    "author": "Rozen",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 171,
                    "name": 'Temple of Time (From "The Legend of Zelda: Ocarina of Time")',
                    "author": "Video Game Players",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 172,
                    "name": 'Molgera (From "The Wind Waker")',
                    "author": "Julia Henderson",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 173,
                    "name": "Ballad of the Wind Fish",
                    "author": "Rozen",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 174,
                    "name": "Sheik's Theme",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 175,
                    "name": "Clock Town Theme",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 176,
                    "name": "Lost Woods",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 177,
                    "name": "Temple Of Time",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 178,
                    "name": "Spirit Tracks Realm Overworld",
                    "author": "Orenji Music",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 179,
                    "name": "Skyloft",
                    "author": "Rozen",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 180,
                    "name": "Fi's Farewell",
                    "author": "Monsalve",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 181,
                    "name": "Hyrule Field",
                    "author": "Dark-Grunt",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                {
                    "id": 182,
                    "name": "Minuet of Forest",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                },
                 {
                    "id": 183,
                    "name": "Forest Temple",
                    "author": "The Marcus Hedges Trend Orchestra",
                    "theme": "Video Game",
                    "subTheme": "The Legend of Zelda",
                    "previewUrl": null
                }
        ]
        """
    }

    fun generateQuestions(settings: Setting): List<QuestionBlindTest> {
        val gson = Gson()
        val itemType = object : TypeToken<List<SongData>>() {}.type

        val allSongs: List<SongData> = gson.fromJson(QUESTIONS_JSON_DATA, itemType) ?: emptyList()

        // filtre a bonne reponse
        val filteredSongs = allSongs.filter { song ->
            val reqThemeName = settings.theme.name
            val reqSubThemeLabel = settings.subTheme?.label
            val hasThemeFilter = settings.theme != Theme.TOUT
            val hasSubThemeFilter = settings.subTheme != null

            when {
                hasSubThemeFilter -> song.subTheme == reqSubThemeLabel
                hasThemeFilter -> song.theme == reqThemeName
                else -> true
            }
        }

        // filtre secu si pas asser de bonne réponse et oui j'ai fait ça pour toi barth
        val sameThemeSongs = if (settings.theme != Theme.TOUT) {
            allSongs.filter { it.theme == settings.theme.name }
        } else {
            allSongs
        }

        val shuffledSongs = filteredSongs.shuffled()
        val limit = if (shuffledSongs.isNotEmpty()) min(settings.nb, shuffledSongs.size) else 0
        val selectedSongs = shuffledSongs.take(limit)

        val questions = selectedSongs.map { correctSong ->

            var potentialWrongAnswers = filteredSongs.filter { it.name != correctSong.name }

            if (potentialWrongAnswers.size < 3) {
                potentialWrongAnswers = sameThemeSongs.filter { it.name != correctSong.name }
            }

            if (potentialWrongAnswers.size < 3) {
                potentialWrongAnswers = allSongs.filter { it.name != correctSong.name }
            }

            val wrongAnswers = potentialWrongAnswers
                .shuffled()
                .take(3)
                .map { it.name }

            QuestionBlindTest(
                title = correctSong.name,
                author = correctSong.author,
                falseChose1 = wrongAnswers.getOrElse(0) { "Lumière" },
                falseChose2 = wrongAnswers.getOrElse(1) { "Une vie a t'aimer" },
                falseChose3 = wrongAnswers.getOrElse(2) { "Alicia" }
            )
        }
        // tkt ça marche enfin je crois euh ...
        return questions
    }
}