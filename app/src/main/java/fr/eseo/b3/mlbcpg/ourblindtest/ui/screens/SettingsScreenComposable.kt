package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting
import fr.eseo.b3.mlbcpg.ourblindtest.model.SubTheme
import fr.eseo.b3.mlbcpg.ourblindtest.model.Theme
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.ui.theme.OurBlindTestTheme
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.OurBlindTestViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SettingsScreen(onValidate: () -> Unit,
               inGameVm: InGameViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold (
            topBar = {
                OurBlindTestAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                )
            },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    SettingsScreenContent(
                        modifier = Modifier.fillMaxWidth(),
                        onValidate = onValidate,
                        inGameVm =  inGameVm
                    )
                }
            }
        )
    }
}

@Composable
private fun SettingsScreenContent(
    modifier: Modifier,
    onValidate: () -> Unit,
    inGameVm: InGameViewModel,
) {

    val currentSetting by inGameVm.setting.collectAsState()

    var nb by remember { mutableIntStateOf(currentSetting.nb) }
    var theme by remember { mutableStateOf(currentSetting.theme) }
    var subTheme by remember { mutableStateOf<SubTheme?>(currentSetting.subTheme) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text("Paramètres du Blind Test", fontSize = 28.sp)

            Spacer(Modifier.height(24.dp))

            // ---- NB de questions ----
            Text("Nombre de questions : $nb", fontSize = 18.sp)

            Slider(
                value = nb.toFloat(),
                onValueChange = { nb = it.toInt() },
                valueRange = 1f..20f,
                steps = 19
            )

            Spacer(Modifier.height(24.dp))

            // ---- Choix du thème ----
            Text("Thème", fontSize = 18.sp)

            ThemeDropdown(
                current = theme,
                onChange = { theme = it
                    subTheme = theme.availableSubThemes.firstOrNull()}
            )

            Spacer(Modifier.height(24.dp))

            // ---- Choix du sous-thème ----
            if (theme.availableSubThemes.isNotEmpty()) {
                Text("Sous-thème", fontSize = 18.sp)

                SubThemeDropdown(
                    current = subTheme,
                    items = theme.availableSubThemes,
                    onChange = { subTheme = it }
                )

                Spacer(Modifier.height(24.dp))
            }

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    inGameVm.updateSetting(nb, theme, subTheme) // subTheme nullable
                    onValidate()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Valider les paramètres")
            }
        }
    }
}

@Composable
fun ThemeDropdown(
    current: Theme,
    onChange: (Theme) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(current.label)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Theme.values().forEach { value ->
                DropdownMenuItem(
                    text = { Text(value.label) },
                    onClick = {
                        onChange(value)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SubThemeDropdown(
    current: SubTheme?,
    items: List<SubTheme>,
    onChange: (SubTheme?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(current?.label ?: "Aucun")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Option "aucun"
            DropdownMenuItem(
                text = { Text("Aucun") },
                onClick = {
                    onChange(null)
                    expanded = false
                }
            )
            items.forEach { value ->
                DropdownMenuItem(
                    text = { Text(value.label) },
                    onClick = {
                        onChange(value)
                        expanded = false
                    }
                )
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val fakeViewModelInGame = InGameViewModel(repository = InGameRepositoryListImpl())


    OurBlindTestTheme {
        SettingsScreen(
            onValidate = {},
            inGameVm = fakeViewModelInGame
        )
    }
}