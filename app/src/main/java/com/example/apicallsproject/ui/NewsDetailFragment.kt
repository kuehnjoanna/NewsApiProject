package com.example.apicallsproject.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        viewModel.selectedArticle.observe(viewLifecycleOwner) {
            binding.tvNewsTitle.text = it.title
            binding.TVvontentNews.text = it.content
            binding.ivNews.load(it.urlToImage) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
            }
            val url = it.url
            binding.moreTV.text = "Read more here"
            binding.moreTV.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(url)
                }
                startActivity(intent)
            }

            binding.fab.setOnClickListener {
                viewModel.addToFav(viewModel.selectedArticle.value!!)
                Toast.makeText(requireContext(), "Article Added to favorites", Toast.LENGTH_SHORT).show()
            }

        }

    }
}