package org.d3if4062.assesment1.ui.histori

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if4062.assesment1.R
import org.d3if4062.assesment1.databinding.ItemHistoriBinding
import org.d3if4062.assesment1.db.PersegiPanjangEntity
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<PersegiPanjangEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PersegiPanjangEntity?>() {
                override fun areItemsTheSame(
                    oldData: PersegiPanjangEntity, newData: PersegiPanjangEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: PersegiPanjangEntity, newData: PersegiPanjangEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        @SuppressLint("StringFormatMatches")
        fun bind(item: PersegiPanjangEntity) = with(binding) {

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            persegipanjangTextView.text = root.context.getString(R.string.hasil_x, item.keliling)
            dataTextView.text = root.context.getString(R.string.data_x, item.luas)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
}
