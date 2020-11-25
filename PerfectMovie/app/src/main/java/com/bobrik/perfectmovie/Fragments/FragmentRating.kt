package com.bobrik.perfectmovie.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobrik.perfectmovie.AboutActivity.AboutActivity
import com.bobrik.perfectmovie.BuildConfig
import com.bobrik.perfectmovie.Models.data
import com.bobrik.perfectmovie.Movie.Movie
import com.bobrik.perfectmovie.R
import com.bobrik.perfectmovie.RecyclerAdapter.Adapter
import kotlinx.android.synthetic.main.fragment_rating.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentRating : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rating: Movie
    private var items: MutableList<data> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        rating = Movie(BuildConfig.APITop, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentRating().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateRecycler()

        settingRecycler()
    }

    fun updateRecycler() {
        clearRecycler()
        rating.refreshRating()
        updateItems()
    }

    fun settingRecycler() {
        RecyclerRating.layoutManager = LinearLayoutManager(this.requireContext())

        RecyclerRating.setHasFixedSize(true)

        RecyclerRating.adapter = Adapter( items, true){

            val intent : Intent = Intent(this.requireContext(), AboutActivity::class.java)
            intent.putExtra("info_key", it)
            startActivity(intent)
        }
    }

    fun updateItems() {
        for (count in 1..rating.getCount()) {
            items.add(data(rating.getTitle(count - 1), "https://image.tmdb.org/t/p/w200/" + rating.getUrlToImage(count - 1), rating.getRating(count - 1), rating.getReleaseDate(count - 1), rating.getDescription(count -1)))
        }
    }

    fun clearRecycler() {
        RecyclerRating.recycledViewPool.clear()

        items.clear()
    }
}