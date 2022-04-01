package org.d3if4062.assesment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if4062.assesment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnKeliling.setOnClickListener {keliling()}
        binding.btnLuas.setOnClickListener { luas() }
    }
    private fun keliling() {
        val panjang = binding.etPanjang.text.toString()
        val lebar = binding.etLebar.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(this, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val hasilKeliling = 2*(panjang.toDouble() + lebar.toDouble())
        binding.tvKeliling.text=getString (R.string.keliling_x,hasilKeliling)
    }
    private fun luas() {
        val panjang = binding.etPanjang.text.toString()
        val lebar = binding.etLebar.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(this, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val hasilLuas= (panjang.toDouble() * lebar.toDouble())
        binding.tvLuas.text=getString (R.string.luas_x,hasilLuas)
    }
}