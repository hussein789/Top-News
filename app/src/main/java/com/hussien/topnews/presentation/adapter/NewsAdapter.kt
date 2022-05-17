package com.hussien.topnews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hussien.topnews.data.model.Article
import com.hussien.topnews.databinding.NewsItemLayoutBinding

class NewsAdapter(
    val newsCallback:(article:Article)->(Unit)
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    val callback = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer<Article>(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(NewsItemLayoutBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.itemView.setOnClickListener {
            newsCallback(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        private val binding:NewsItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source?.name

            Glide.with(binding.ivArticleImage.context).
            load(article.urlToImage).
            into(binding.ivArticleImage)
        }
    }
}