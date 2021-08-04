package com.noweto.moviemix.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.noweto.moviemix.R
import com.noweto.moviemix.core.utils.BusinessConst.BASE_IMAGE_PATH
import com.noweto.moviemix.core.utils.BusinessConst.MOVIE_DETAILS
import com.noweto.moviemix.ui.home.models.MovieResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {


    lateinit var  movieItem : MovieResult

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrieveData()
        hideBottomNav()


    }


    @SuppressLint("SetTextI18n")
    private fun retrieveData() {
        movieItem =arguments?.getSerializable(MOVIE_DETAILS) as MovieResult

        Glide.with(requireContext()).load(BASE_IMAGE_PATH+movieItem.poster_path).into(moviePoster)

        movieName.text = movieItem.title
        movieRating.text = movieItem.vote_average.toString()
        movieOverView.text = movieItem.overview
       // val popInt = movieItem.popularity.toInt()
        moviePopularity.text = "${movieItem.popularity}  Watch it"

    }

    private fun hideBottomNav() {
        val v : View? = activity?.findViewById(R.id.bottom_nav)
        if (v != null) {
            v.visibility = GONE
        }
    }


}