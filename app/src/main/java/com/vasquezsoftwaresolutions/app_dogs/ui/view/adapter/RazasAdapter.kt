package com.vasquezsoftwaresolutions.app_dogs.ui.view.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.vasquezsoftwaresolutions.app_dogs.databinding.RazasItemListBinding
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.RazasEntity

/**
 *@autor Pablo
 *@create 08-06-2024 20:32
 *@project app_dogs
 *@Version 1.0
 */
class RazasAdapter : RecyclerView.Adapter<RazasAdapter.RazasDogVH>() {

    private var listBreed = listOf<RazasEntity>()
    private val selectedBreed = MutableLiveData<RazasEntity>()

    fun update(list: List<RazasEntity>) {
        listBreed = list
        notifyDataSetChanged()
    }

    fun selectedBreed(): LiveData<RazasEntity> = selectedBreed

    inner class RazasDogVH(private val binding: RazasItemListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(razas: RazasEntity) {
            binding.tvbreed.text = razas.breed
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedBreed.value = listBreed[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RazasDogVH {
        return RazasDogVH(RazasItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RazasDogVH, position: Int) {
        holder.bind(listBreed[position])
    }

    override fun getItemCount() = listBreed.size

}