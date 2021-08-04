package com.noweto.moviemix.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noweto.moviemix.R
import com.noweto.moviemix.core.utils.BusinessConst.BASE_IMAGE_PATH
import com.noweto.moviemix.ui.home.models.MovieResult


class MovieAdapter(private val onItemClick: OnItemClick) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var movieList:ArrayList<MovieResult> = arrayListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list:ArrayList<MovieResult>){
        movieList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieHolder(v)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindData(movieList[position])

        //~~ on click item
        holder.itemView.setOnClickListener {
            onItemClick.onItemClicked(movieList[position])
        }



    }



    override fun getItemCount(): Int = movieList.size


    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val movieImage :ImageView= itemView.findViewById(R.id.movieImage)


        fun bindData(movieResult: MovieResult){
            Glide.with(movieImage.context).load(BASE_IMAGE_PATH+movieResult.poster_path).into(movieImage)
        }



    }

    interface OnItemClick {
         fun onItemClicked(movieResult: MovieResult)
    }




}