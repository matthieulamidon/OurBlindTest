package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepository

class InGameViewModelFactory(private val repository: InGameRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InGameViewModel::class.java)) {
            @Suppress("UNCHECKD_CAST")
            return InGameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
