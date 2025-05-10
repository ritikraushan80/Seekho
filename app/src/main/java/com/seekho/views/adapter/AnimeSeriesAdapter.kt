package com.seekho.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.seekho.R
import com.seekho.databinding.AnimeSeriesItemBinding
import com.seekho.model.AnimeSeriesList
import com.seekho.utils.CallbackListener


/**
 * Created by Ritik on: 10/05/25
 */

class AnimeSeriesAdapter(
    private val list: ArrayList<AnimeSeriesList>, private val onClick: CallbackListener
) : RecyclerView.Adapter<AnimeSeriesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AnimeSeriesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AnimeSeriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.title.text = item.title?:"N/A"
        holder.binding.episodes.text = "Number of Episodes: ${item.episodes?:"N/A"}"
        holder.binding.rating.text = "Rating: ${item.rating?:"N/A"}"

        holder.binding.imgPoster.load(item.images?.jpg?.largeImageUrl) {
            crossfade(true)
            placeholder(R.drawable.logo)
        }

        holder.itemView.setOnClickListener {
            onClick.clickOnAnimeItem(item)
        }
    }
}