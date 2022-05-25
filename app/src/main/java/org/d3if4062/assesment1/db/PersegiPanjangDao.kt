package org.d3if4062.assesment1.db
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersegiPanjangDao {
    @Insert
    fun insert(persegiPanjangDao: PersegiPanjangEntity)
    @Query("SELECT * FROM `persegi panjang` ORDER BY id DESC")
    fun getLastPersegiPanjang(): LiveData<PersegiPanjangEntity?>

    @Query("DELETE FROM `persegi panjang`")
    fun clearData()
}