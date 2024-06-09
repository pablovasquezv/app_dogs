package com.vasquezsoftwaresolutions.app_dogs.ui.view.adapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vasquezsoftwaresolutions.app_dogs.R
import com.vasquezsoftwaresolutions.app_dogs.databinding.ImageListItemBinding
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.DogsImagesEntity

/**
 *@autor Pablo
 *@create 08-06-2024 20:12
 *@project app_dogs
 *@Version 1.0
 */
class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageDogVH>() {

    var listImages = listOf<DogsImagesEntity>()
    val selectedImage = MutableLiveData<DogsImagesEntity>()

    fun update(list: List<DogsImagesEntity>) {
        listImages = list
        notifyDataSetChanged()
    }

    fun selectedImage(): LiveData<DogsImagesEntity> = selectedImage
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDogVH {
        return ImageDogVH(ImageListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageDogVH, position: Int) {
        holder.bind(listImages[position])
    }

    override fun getItemCount() = listImages.size

    inner class ImageDogVH(private val binding: ImageListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        fun bind(image: DogsImagesEntity) {
            Glide.with(binding.root).load(image.imageUrl).into(binding.ivDog)
            if (image.fav) {
                binding.ivFab.setImageDrawable(ContextCompat.getDrawable(itemView.context,
                    R.drawable.baseline_favorite_24))
                binding.ivFab.setColorFilter(Color.RED)
                binding.ivFab.visibility = View.VISIBLE
            } else {
                binding.ivFab.setImageDrawable(ContextCompat.getDrawable(
                    itemView.context, R.drawable.twotone_favorite_24))
                binding.ivFab.setColorFilter(Color.GRAY)
                binding.ivFab.visibility = View.VISIBLE
            }
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            selectedImage.value = listImages[adapterPosition]
            return true
        }

    }

}