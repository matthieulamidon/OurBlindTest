package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
            topBar = { OurBlindTestAppBar(modifier = Modifier.fillMaxWidth()) },
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

// Contenu principal de l'écran de paramètres
@Composable
private fun SettingsScreenContent(
    modifier: Modifier,
    onValidate: () -> Unit,
    inGameVm: InGameViewModel,
) {
    val allThemes = Theme.values().toList()

    val currentSetting by inGameVm.setting.collectAsState()
    var nb by remember { mutableIntStateOf(currentSetting.nb) }
    var theme by remember { mutableStateOf(currentSetting.theme) }
    var subTheme by remember { mutableStateOf<SubTheme?>(currentSetting.subTheme) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Pour coller le bouton en bas
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                "Paramètres du Blind Test",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(40.dp))

            //Nombre de questions
            Text(
                "Nombre de questions : $nb",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            Slider(
                value = nb.toFloat(),
                onValueChange = { nb = it.toInt() },
                valueRange = 1f..20f,
                steps = 19,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(40.dp))

            //Choix du thème
            SectionTitle("Thème")
            
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                allThemes.forEach { currentTheme ->
                    FilterChip(
                        selected = theme == currentTheme,
                        onClick = {
                            if (theme != currentTheme) {
                                theme = currentTheme
                                subTheme = currentTheme.availableSubThemes.firstOrNull()
                            }
                        },
                        label = { Text(currentTheme.label) },
                        leadingIcon = {
                            if (theme == currentTheme) {
                                Icon(Icons.Filled.Check, contentDescription = "Sélectionné")
                            }
                        }
                    )
                }
            }

            Spacer(Modifier.height(40.dp))

            // Choix du sous-thème 
            if (theme.availableSubThemes.isNotEmpty()) {
                SectionTitle("Sous-thème")

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    /* Mode aléatoire qui ne fonctionne pas encore
                    FilterChip(
                        selected = subTheme == null,
                        onClick = { subTheme = null },
                        label = { Text("Aléatoire") },
                        leadingIcon = {
                            if (subTheme == null) {
                                Icon(Icons.Filled.Check, contentDescription = "Aucun sélectionné")
                            }
                        }
                    )
                    */
                    theme.availableSubThemes.forEach { currentSubTheme ->
                        FilterChip(
                            selected = subTheme == currentSubTheme,
                            onClick = { subTheme = currentSubTheme },
                            label = { Text(currentSubTheme.label) },
                            leadingIcon = {
                                if (subTheme == currentSubTheme) {
                                    Icon(Icons.Filled.Check, contentDescription = "Sélectionné")
                                }
                            }
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
        }

        Spacer(Modifier.height(40.dp))

        Button(
            onClick = {
                inGameVm.updateSetting(nb, theme, subTheme)
                onValidate()
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp)
        ) {
            Text("Valider les paramètres", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
        Spacer(Modifier.height(16.dp))
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