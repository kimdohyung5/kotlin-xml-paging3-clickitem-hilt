package com.kimdo.picturepaging3withhilt.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.kimdo.picturepaging3withhilt.R
import com.kimdo.picturepaging3withhilt.databinding.FragmentPictureDetailBinding


class PictureDetailFragment: Fragment() {

    private var _binding: FragmentPictureDetailBinding? = null
    private val binding: FragmentPictureDetailBinding get() = _binding!!

    val args: PictureDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = args.url
        binding.webview.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        binding.webview.loadUrl( url )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
