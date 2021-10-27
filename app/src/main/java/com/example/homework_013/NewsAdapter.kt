package com.example.homework_013

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_013.databinding.NewsCardBinding
import com.example.homework_013.extensions.glideExtension
import com.example.homework_013.model.NewsItem

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var list: MutableList<NewsItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<NewsItem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NewsCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: NewsCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var newsData: NewsItem
        fun onBind() {

            newsData = list[adapterPosition]
            // binding.id.text = newsData.id.toString()
            binding.title.text = newsData.title
            binding.date.text = newsData.date
            binding.description.text = newsData.description
            binding.image.glideExtension(newsData.imgUrl)

            /*Glide.with(itemView)
                    .load(newsData.imgUrl)
                    .into(binding.image)*/
        }

    }

}