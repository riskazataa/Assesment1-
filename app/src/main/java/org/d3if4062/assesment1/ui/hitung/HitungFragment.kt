package org.d3if4062.assesment1.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if4062.assesment1.R
import org.d3if4062.assesment1.databinding.FragmentHitungBinding
import org.d3if4062.assesment1.model.HasilPersegiPanjang
import org.d3if4062.assesment1.db.PersegiPanjangDb


class HitungFragment : Fragment(){
    lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = PersegiPanjangDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnKeliling.setOnClickListener { hitung() }
        binding.btnShare.setOnClickListener { shareData() }
        viewModel.getHasilPersegiPanjang().observe(requireActivity(), { showResult(it) })
    }
    private fun hitung() {
        val Panjang = binding.etPanjang.text.toString()
        if (TextUtils.isEmpty(Panjang)) {
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val Lebar = binding.etLebar.text.toString()
        if (TextUtils.isEmpty(Lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

    }
    private fun shareData() {
        val message = getString(
            R.string.bagikan_template,
            binding.tvKeliling.text,
            binding.tvLuas.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }


    private fun showResult(result: HasilPersegiPanjang?) {
        if (result == null) return
        binding.tvKeliling.text = getString(R.string.keliling_x, result.keliling)
        binding.tvLuas.text = getString(R.string.luas_x, result.luas)
    }

}