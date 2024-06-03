package com.example.apicallsproject.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.apicallsproject.R
import com.example.apicallsproject.adapter.NewsAdapter
import com.example.apicallsproject.data.model.Article
import com.example.apicallsproject.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {
    private val viewModel: NewsViewModel by activityViewModels()

    private lateinit var binding: FragmentSearchBinding

    lateinit var errorText: TextView
    lateinit var itemSearchError: CardView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemClickedCallback: (Article) -> Unit = {
            viewModel.selectedArticleItem(it)
            findNavController().navigate(R.id.newsDetailFragment)
        }
        viewModel.articles.observe(viewLifecycleOwner){
            val adapter = NewsAdapter(it, itemClickedCallback)
            binding.recyclerSearch.adapter = adapter
        }
        viewModel.loadImages()

        binding.searchEdit.addTextChangedListener(object : TextWatcher {

            // Die beforeTextChanged-Funktion wird aufgerufen, bevor sich der Text ändert
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.filterArticles(s.toString())
            }

            // Die onTextChanged-Funktion wird während der Änderung aufgerufen
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            // Die afterTextChanged-Funktion wird nach der Änderung aufgerufen
            override fun afterTextChanged(s: Editable?) {


            }

//hier fehler


        })
    }



    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMessage(){
        itemSearchError.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String){
        itemSearchError.visibility = View.VISIBLE
        errorText.text = message
        isError = true
    }
}