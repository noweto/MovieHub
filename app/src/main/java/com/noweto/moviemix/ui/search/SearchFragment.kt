package com.noweto.moviemix.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.noweto.moviemix.R
import com.noweto.moviemix.core.utils.BusinessConst.MOVIE_DETAILS
import com.noweto.moviemix.ui.home.adapter.MovieAdapter
import com.noweto.moviemix.ui.home.models.MovieResult
import com.noweto.moviemix.ui.search.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : Fragment(),MovieAdapter.OnItemClick {

    private val viewModel : SearchViewModel by viewModels()
    private val adapter : MovieAdapter = MovieAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setUpSearch()
        showNav()



    }



    private fun setUpSearch(){
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val movieName = s.toString()
                setUpObserver(movieName)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun initViews(){
        searchMovieRv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpObserver(movieName:String){
        viewModel.getMovieByName(movieName).observe(viewLifecycleOwner, Observer {
            when(it.status){
                com.noweto.moviemix.core.utils.Resource.Status.REMOTE_SUCCESS->{
                    Log.e("Search_Case","success"+it.data.toString())
                    adapter.setData(it.data?.results as ArrayList<MovieResult>)
                    searchMovieRv.adapter = adapter

                }
                com.noweto.moviemix.core.utils.Resource.Status.ERROR->{
                    Log.e("Search_Case","Error"+it.message.toString())
                }
                com.noweto.moviemix.core.utils.Resource.Status.LOADING->{
                    Log.e("Search_Case","loading"+it.message.toString())
                }
            }
        })

    }

    override fun onItemClicked(movieResult: MovieResult) {
        findNavController().navigate(R.id.from_search_to_details, bundleOf(MOVIE_DETAILS to movieResult))
    }
    private fun showNav(){

        val v : View? = activity?.findViewById(R.id.bottom_nav)
        if (v != null&&v.visibility== View.GONE) {
            v.visibility = View.VISIBLE
        }


    }


}