package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eseo.b3.mlbcpg.ourblindtest.model.Score
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OurBlindTestViewModel (val repository : OurBlindTestRepository): ViewModel(){
    private val _scores = MutableStateFlow<List<Score>>(emptyList())
    val scores : StateFlow<List<Score>> = _scores.asStateFlow()

    private val _score = MutableStateFlow<Score?>(null)
    val score : StateFlow<Score?> = _score.asStateFlow()

    init{
        refreshScores()
    }

    fun refreshScores() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getAllScore()
            }
            _scores.value = result
        }
    }

    fun addOrUpdatescore(score : Score) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.addOrUpdateScore(score)
            }
        }
    }

    fun getscoreById(scoreId : String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getScoreById(scoreId)
            }
            _score.value = result
        }
    }

    fun deletescore(score : Score) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteScore(score)
            }
            refreshScores()
        }
    }
}