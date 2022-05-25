package org.d3if4062.assesment1.ui.hitung

import org.d3if4062.assesment1.db.PersegiPanjangDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HitungViewModelFactory (
    private val db: PersegiPanjangDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}