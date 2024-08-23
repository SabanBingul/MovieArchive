package com.sabanbingul.moviearchive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sabanbingul.moviearchive.databinding.ItemCarouselBinding

class CarouselAdapter(private val imageList : MutableList<Int>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    class CarouselViewHolder(private val binding : ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image : Int){
            binding.carouselImageView.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(ItemCarouselBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

}