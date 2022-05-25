package org.d3if4062.assesment1.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4062.assesment1.db.PersegiPanjangDao
import org.d3if4062.assesment1.db.PersegiPanjangEntity

class HitungViewModel(private val db: PersegiPanjangDao) : ViewModel() {
    private val hasilPersegi = MutableLiveData<HasilPersegiPanjang?>()

    fun hitungPersegiPanjang(panjang: Float, lebar:Float) {
        val keliling = 2 * panjang + lebar
        val luas = panjang * lebar
        hasilPersegi.value = HasilPersegiPanjang(keliling, luas)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataPersegiPanjang = PersegiPanjangEntity(
                    keliling = keliling,
                    luas = luas,
                )
                db.insert(dataPersegiPanjang)
            }
        }
    }
}