package org.d3if4062.assesment1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persegi panjang")
data class PersegiPanjangEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var keliling: Float,
    var luas: Float
)