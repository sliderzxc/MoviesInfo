package com.test.movieapplication.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.movieapplication.R
import com.test.movieapplication.databinding.ItemFilmBinding
import com.test.movieapplication.network.model.Result
import com.test.movieapplication.utils.other.MainConstants.IMAGE_BASE_URL

class DiffUtilsPaging : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}

class FilmsPagingAdapter(
    private val onClickItem: (Result) -> Unit
) : PagingDataAdapter<Result, FilmsPagingAdapter.FilmsPagingViewHolder>(
    DiffUtilsPaging()
) {

    class FilmsPagingViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding by lazy { ItemFilmBinding.bind(item) }
        fun bind(result: Result, onClickItem: () -> Unit) {
            binding.title.text = result.title
            binding.date.text = result.release_date
            Glide.with(binding.root).load("${IMAGE_BASE_URL}${result.poster_path}").into(binding.image)
            binding.root.setOnClickListener { onClickItem() }
        }
    }

    override fun onBindViewHolder(holder: FilmsPagingViewHolder, position: Int) {
        holder.bind(getItem(position)!!) { getItem(position)?.let { onClickItem(it) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsPagingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmsPagingViewHolder(view)
    }

}