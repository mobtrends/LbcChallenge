package com.example.lbcchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lbcchallenge.R
import com.example.lbcchallenge.databinding.CellAlbumBinding
import com.example.lbcchallenge.domain.DisplayableAlbum
import com.squareup.picasso.Picasso

class AlbumsAdapter(private val displayableAlbums: List<DisplayableAlbum>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val albumBinding =
            CellAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumsViewHolder(albumBinding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.albumTitleTextView.text = displayableAlbums[position].title
        Picasso.get()
            .load(displayableAlbums[position].imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.albumImageView)
    }

    override fun getItemCount(): Int = displayableAlbums.size

    inner class AlbumsViewHolder(binding: CellAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val albumTitleTextView = binding.albumTitleTextView
        val albumImageView = binding.albumImageView
    }
}