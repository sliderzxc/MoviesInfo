package com.test.movieapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.movieapplication.R
import com.test.movieapplication.databinding.ItemFilmForFavoriteBinding
import com.test.movieapplication.network.model.Result
import com.test.movieapplication.utils.other.MainConstants

class ToDoDiffUtilsStart(
    private val oldList: List<Result>,
    private val newList: List<Result>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldToDo = oldList[oldItemPosition]
        val newToDo = newList[newItemPosition]
        return oldToDo.id == newToDo.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldToDo = oldList[oldItemPosition]
        val newToDo = newList[newItemPosition]
        return oldToDo == newToDo
    }
}

class DetailsFragmentAdapter(
    private val onClickItem: (Result) -> Unit
) : RecyclerView.Adapter<DetailsFragmentAdapter.DetailsFragmentViewHolder>() {

    private var listOfFilms: List<Result> = listOf()
    var films = MutableLiveData(0)

    class DetailsFragmentViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding by lazy { ItemFilmForFavoriteBinding.bind(item) }

        fun bind(result: Result, onClickItem: (Result) -> Unit) {
            binding.title.text = result.title
            binding.date.text = result.release_date
            Glide.with(binding.root).load("${MainConstants.IMAGE_BASE_URL}${result.poster_path}").into(binding.image)
            binding.root.setOnClickListener { onClickItem(result) }
        }
    }

    override fun getItemCount(): Int {
        films.value = listOfFilms.size
        return listOfFilms.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsFragmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film_for_favorite, parent, false)
        return DetailsFragmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsFragmentViewHolder, position: Int) {
        holder.bind(listOfFilms[position], onClickItem)
    }

    fun setList(list: List<Result>) {
        val diffUtilsCallback = ToDoDiffUtilsStart(listOfFilms, list)
        val difResult = DiffUtil.calculateDiff(diffUtilsCallback, true)
        listOfFilms = list
        difResult.dispatchUpdatesTo(this)
    }
}