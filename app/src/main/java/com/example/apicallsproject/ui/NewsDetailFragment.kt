package com.example.apicallsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.apicallsproject.R
import com.example.apicallsproject.databinding.FragmentNewsBinding
import com.example.apicallsproject.databinding.FragmentNewsDetailBinding


class NewsDetailFragment : Fragment() {

    private val viewModel: NewsViewModel by activityViewModels()

    private lateinit var binding: FragmentNewsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
binding = FragmentNewsDetailBinding.inflate(layoutInflater)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedArticle.observe(viewLifecycleOwner){
            binding.tvNewsTitle.text = it.title
            binding.TVvontentNews.text = it.content
            binding.ivNews.load(it.urlToImage){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
            }
        }
    }

}