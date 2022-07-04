package com.example.lbcchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.lbcchallenge.R
import com.example.lbcchallenge.databinding.CellAlbumBinding
import com.example.lbcchallenge.domain.DisplayableAlbum

class AlbumsAdapter(private val displayableAlbums: List<DisplayableAlbum>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val albumBinding =
            CellAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumsViewHolder(albumBinding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.albumTitleTextView.text = displayableAlbums[position].title

        // Need to add a user-agent to resolve the status code 410 of the HttpException return by Glide
        val imageUrl = GlideUrl(
            displayableAlbums[position].imageUrl,
            LazyHeaders.Builder().addHeader(USER_AGENT, ANDROID_USER_AGENT)
                .build()
        )

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.albumImageView)
    }

    override fun getItemCount(): Int = displayableAlbums.size

    inner class AlbumsViewHolder(binding: CellAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val albumTitleTextView = binding.albumTitleTextView
        val albumImageView = binding.albumImageView
    }

    companion object {
        private const val USER_AGENT = "User-Agent"
        private const val ANDROID_USER_AGENT =
            "Mozilla/5.0 (Linux; Android 12) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.70 Mobile Safari/537.36"
    }
}