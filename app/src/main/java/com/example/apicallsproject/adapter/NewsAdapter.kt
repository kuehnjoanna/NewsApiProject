package com.example.apicallsproject.adapter

import android.service.autofill.Dataset
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apicallsproject.R
import com.example.apicallsproject.data.model.Article
import com.example.apicallsproject.data.model.NewsResponse
import com.example.apicallsproject.databinding.NewsItemBinding

class NewsAdapter(
    val dataset: NewsResponse
) : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: NewsItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset.articles[position]
        Log.d("adapter", "${item.urlToImage}")
        holder.binding.tvNews.text = item.description
        holder.binding.ivNews.load(item.urlToImage){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }
    }

    override fun getItemCount(): Int {
        return dataset.articles.size
    }
}