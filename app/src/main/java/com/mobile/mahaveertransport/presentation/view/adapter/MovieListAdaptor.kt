package com.mobile.mahaveertransport.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mahaveertransport.databinding.ItemMovieBinding
import com.mobile.mahaveertransport.domain.datamodel.Movie
import  com.mobile.mahaveertransport.presentation.view.viewholder.MovieViewHolder
import javax.inject.Inject
import kotlin.properties.Delegates

typealias MovieClickListener = (Movie) -> Unit
class MovieListAdaptor @Inject constructor() : RecyclerView.Adapter<MovieViewHolder>() {

    lateinit var listner: MovieClickListener
    var dataSet: List<Movie> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            parent.context, ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = dataSet[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            listner.invoke(movie)
        }
    }

    override fun getItemCount() = dataSet.size


}