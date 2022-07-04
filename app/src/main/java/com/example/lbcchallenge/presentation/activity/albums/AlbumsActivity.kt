package com.example.lbcchallenge.presentation.activity.albums

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lbcchallenge.LbcChallengeApplication
import com.example.lbcchallenge.databinding.ActivityAlbumsBinding
import com.example.lbcchallenge.presentation.StateChild
import com.example.lbcchallenge.presentation.adapter.AlbumsAdapter
import com.example.lbcchallenge.presentation.di.components.DaggerAlbumsComponent
import com.example.lbcchallenge.presentation.di.modules.AlbumsModule
import com.example.lbcchallenge.presentation.state
import com.example.lbcchallenge.presentation.viewmodels.albums.AlbumsDisplayState
import com.example.lbcchallenge.presentation.viewmodels.albums.AlbumsViewModel
import javax.inject.Inject

class AlbumsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumsBinding
    private lateinit var albumsAdapter: AlbumsAdapter

    @Inject
    lateinit var viewModel: AlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        injectDependencies()
        binding.swipeContainer.setOnRefreshListener { viewModel.getAlbums() }
        binding.errorLayout.tryAgainButton.setOnClickListener { viewModel.getAlbums() }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.displayState.observe(this) { displayState ->
            when (displayState) {
                AlbumsDisplayState.Error -> binding.albumsViewFlipper.state = StateChild.ERROR
                AlbumsDisplayState.Loading -> binding.albumsViewFlipper.state = StateChild.LOADING
                is AlbumsDisplayState.Success -> {
                    binding.swipeContainer.isRefreshing = false
                    albumsAdapter = AlbumsAdapter(displayState.albums)
                    binding.albumsRecyclerView.adapter = albumsAdapter
                    binding.albumsRecyclerView.layoutManager = LinearLayoutManager(this)
                    binding.albumsViewFlipper.state = StateChild.CONTENT
                }

            }
        }
    }

    private fun injectDependencies() {
        DaggerAlbumsComponent.builder()
            .applicationComponent((applicationContext as LbcChallengeApplication).applicationComponent)
            .albumsModule(AlbumsModule(this))
            .build()
            .inject(this)
    }
}