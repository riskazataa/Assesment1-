package org.d3if4062.assesment1.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4062.assesment1.db.PersegiPanjangDao
import org.d3if4062.assesment1.db.PersegiPanjangEntity
import org.d3if4062.assesment1.model.HasilPersegiPanjang

class HitungViewModel(private val db: PersegiPanjangDao) : ViewModel() {
    private val hasilPersegiPanjang = MutableLiveData<HasilPersegiPanjang?>()

    fun hitungPersegiPanjang(panjang: Float, lebar: Float) {
        val keliling = 2 * panjang + lebar
        val luas = panjang * lebar
        hasilPersegiPanjang.value = HasilPersegiPanjang(keliling, luas)
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

    fun getHasilPersegiPanjang(): LiveData<HasilPersegiPanjang?> = hasilPersegiPanjang

}