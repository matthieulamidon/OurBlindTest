package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepository

class OurBlindTestViewModelFactory(private val repository : OurBlindTestRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(OurBlindTestViewModel::class.java)) {
            @Suppress("UNCHECKD_CAST")
            return OurBlindTestViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}