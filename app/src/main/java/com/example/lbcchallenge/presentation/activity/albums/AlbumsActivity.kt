package com.example.lbcchallenge.presentation.activity.albums

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lbcchallenge.LbcChallengeApplication
import com.example.lbcchallenge.databinding.ActivityAlbumsBinding
import com.example.lbcchallenge.presentation.di.components.DaggerAlbumsComponent
import com.example.lbcchallenge.presentation.di.modules.AlbumsModule
import com.example.lbcchallenge.presentation.viewmodels.albums.AlbumsDisplayState
import com.example.lbcchallenge.presentation.viewmodels.albums.AlbumsViewModel
import javax.inject.Inject

class AlbumsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumsBinding

    @Inject
    lateinit var viewModel: AlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        injectDependencies()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.displayState.observe(this) { displayState ->
            when (displayState) {
                AlbumsDisplayState.Error -> {
                }
                AlbumsDisplayState.Loading -> {
                }
                is AlbumsDisplayState.Success -> {
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