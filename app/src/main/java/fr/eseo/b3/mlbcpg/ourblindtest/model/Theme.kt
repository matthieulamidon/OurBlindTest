package fr.eseo.b3.mlbcpg.ourblindtest.model

enum class Theme(
    val label: String,
    val availableSubThemes: List<SubTheme>
) {
    TOUT(
        label = "Tous les thèmes",
        availableSubThemes = emptyList()
    ),

    JEU_VIDEO(
        label = "Jeux vidéo",
        availableSubThemes = listOf(SubTheme.HOLLOW_KNIGHT, SubTheme.PERSONA)
    ),

    ANNEES_2000(
        label = "Années 2000",
        availableSubThemes = emptyList()
    ),

    DESSINS_ANIMES(
        label = "Dessins animés",
        availableSubThemes = listOf(SubTheme.DISNEY, SubTheme.CARTOON_NETWORK)
    ),

    MUSIQUE_NULLE(
        label = "Musique nulle",
        availableSubThemes = emptyList()
    );
}

enum class SubTheme(val label: String) {
    HOLLOW_KNIGHT("Hollow Knight"),
    DISNEY("Disney"),
    CARTOON_NETWORK("Cartoon Network"),
    PERSONA("Persona");
}
