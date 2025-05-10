package com.seekho.views.adapter

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.seekho.databinding.GenresItemBinding
import com.seekho.model.Genres


/**
 * Created by Ritik on: 10/05/25
 */

class GenresAdapter(private val list: ArrayList<Genres>) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: GenresItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GenresItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.name.text = item.name?:"N/A"
        holder.binding.type.text = "Type: ${item.type?:"N/A"}"

        val linkText = "<a href='${item.url}'>${item.url}</a>"
        holder.binding.url.text =
            HtmlCompat.fromHtml("Link: $linkText", HtmlCompat.FROM_HTML_MODE_LEGACY)
        holder.binding.url.movementMethod = LinkMovementMethod.getInstance()

    }
}