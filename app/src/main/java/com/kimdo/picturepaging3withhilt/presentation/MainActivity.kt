package com.kimdo.picturepaging3withhilt.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kimdo.picturepaging3withhilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding  by lazy { ActivityMainBinding.inflate( layoutInflater )}
    val viewModel: PicturePagingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
    }

    companion object {
        const val TAG = "MainActivity"
    }
}