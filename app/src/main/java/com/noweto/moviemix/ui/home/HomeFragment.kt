package com.noweto.moviemix.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.noweto.moviemix.R
import com.noweto.moviemix.core.utils.BusinessConst.MOVIE_DETAILS
import com.noweto.moviemix.core.utils.Resource
import com.noweto.moviemix.ui.home.adapter.MovieAdapter
import com.noweto.moviemix.ui.home.models.MovieResult
import com.noweto.moviemix.ui.home.viewModels.PopMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class HomeFragment : Fragment(),MovieAdapter.OnItemClick {


    private val viewModel : PopMoviesViewModel by viewModels()
    private val adapter : MovieAdapter = MovieAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showNav()
        initViews()
        setUpObserver()
    }


    private fun initViews(){
        popularMovieRv.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setUpObserver() {

        viewModel.popularMoviesList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.REMOTE_SUCCESS->{
                    Log.e("status","from remote"+it.data.toString())
                }
                Resource.Status.LOCAL_SUCCESS->{
                    Log.e("status","from local"+it.data.toString())
                    adapter.setData(it.data as ArrayList<MovieResult>)
                    popularMovieRv.adapter = adapter

                }
                Resource.Status.ERROR->{
                    Log.e("status","Error"+it.message)
                }
                Resource.Status.LOADING->{
                    Log.e("status","Loading")
                }
            }
        })


    }

    override fun onItemClicked(movieResult: MovieResult) {
        findNavController().navigate(R.id.from_home_to_details, bundleOf(MOVIE_DETAILS to movieResult))
    }



    private fun showNav(){
        val v : View? = activity?.findViewById(R.id.bottom_nav)
        if (v != null&&v.visibility==GONE) {
            v.visibility = View.VISIBLE
        }

    }



}