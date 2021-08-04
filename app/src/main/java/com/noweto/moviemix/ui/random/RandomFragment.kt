package com.noweto.moviemix.ui.random

import android.os.Bundle
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
import com.noweto.moviemix.core.utils.BusinessConst
import com.noweto.moviemix.core.utils.Resource
import com.noweto.moviemix.ui.home.adapter.MovieAdapter
import com.noweto.moviemix.ui.home.models.MovieResult
import com.noweto.moviemix.ui.random.viewModels.RandomMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random.*


@AndroidEntryPoint
class RandomFragment : Fragment(),MovieAdapter.OnItemClick {

    private val viewModel : RandomMovieViewModel by viewModels()
    private val adapter : MovieAdapter = MovieAdapter(this)
    private var movieList : ArrayList<MovieResult> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showNav()
        initViews()
        getAllPopularMovie()


    }

    private fun initViews(){
        recommendMovieRv.layoutManager = LinearLayoutManager(requireContext())
        movie_animation.pauseAnimation()
        recommend_word.setOnClickListener {
            getAllPopularMovie()
        }
    }


    private fun getAllPopularMovie(){

        movie_animation.playAnimation()
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.REMOTE_SUCCESS->{
                    Log.e("status","from remote"+it.data.toString())
                }
                Resource.Status.LOCAL_SUCCESS->{
                    Log.e("status","from local"+it.data.toString())
                    movieList = it.data as ArrayList<MovieResult>

                    adapter.setData(viewModel.getRandomMovie(movieList))
                    recommendMovieRv.adapter = adapter
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



    private fun  showNav(){
        val v : View? = activity?.findViewById(R.id.bottom_nav)
        if (v != null&&v.visibility== View.GONE) {
            v.visibility = View.VISIBLE
        }
    }



    override fun onItemClicked(movieResult: MovieResult) {
        findNavController().navigate(R.id.from_random_to_details, bundleOf(BusinessConst.MOVIE_DETAILS to movieResult))
    }


}