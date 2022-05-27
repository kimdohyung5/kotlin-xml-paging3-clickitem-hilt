package com.kimdo.picturepaging3withhilt.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.kimdo.picturepaging3withhilt.R
import com.kimdo.picturepaging3withhilt.databinding.ItemSampleBinding
import com.kimdo.picturepaging3withhilt.domain.model.Picture


class PagingAdapter(private val onClickListener: OnPageItemClickListener ) : PagingDataAdapter<Picture, PagingAdapter.PagingViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  ItemSampleBinding.inflate(layoutInflater, parent, false)
        return PagingViewHolder( binding )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            item?.url?.let { url ->
                onClickListener.onClick( item.url)
            }
        }
        holder.bind( item )
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Picture>() {
            override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnPageItemClickListener {
        fun onClick(url:String )
    }

    class PagingViewHolder(
        private val binding: ItemSampleBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Picture?) {
            data?.let { data ->
                binding.txtId.text = data.id
                binding.txtAuthor.text = data.author
                binding.txtUrl.text = data.url
                binding.ivPicture.load( data.download_url ) {
                    crossfade(true)
                    placeholder(R.drawable.ic_load_24)
                    transformations( CircleCropTransformation() )
                }
            }
        }
    }
}






