package com.kimdo.picturepaging3withhilt.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kimdo.picturepaging3withhilt.databinding.FragmentPictureListBinding
import com.kimdo.picturepaging3withhilt.paging.PagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PictureListFragment: Fragment() {

    private var _binding: FragmentPictureListBinding? = null
    private val binding: FragmentPictureListBinding get() = _binding!!

    private lateinit var pagingAdapter: PagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        mainActivity.lifecycleScope

        pagingAdapter = PagingAdapter( object: PagingAdapter.OnPageItemClickListener {
            override fun onClick(url: String) {
                Log.d(TAG, "onClick: $url")
                val action = PictureListFragmentDirections.toDetail( url = url )
                findNavController().navigate( action )
            }        
        })

        binding.rvView.adapter = pagingAdapter

        mainActivity.lifecycleScope.launch {
            mainActivity.viewModel.pagingData.collectLatest {
                pagingAdapter.submitData(it)
            }
        }

        binding.swRefresh.setOnRefreshListener {
            pagingAdapter.refresh()
            binding.swRefresh.isRefreshing = false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "PictureListFragment"
    }
}