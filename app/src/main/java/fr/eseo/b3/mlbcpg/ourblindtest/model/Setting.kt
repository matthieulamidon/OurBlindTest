package fr.eseo.b3.mlbcpg.ourblindtest.model

data class Setting(
    var nb: Int,
    var theme: Theme,
    var subTheme: SubTheme? = null
)