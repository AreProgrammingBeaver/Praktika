package com.bobrik.perfectmovie.AboutActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bobrik.perfectmovie.Models.data
import com.bobrik.perfectmovie.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val item : data? = intent.getParcelableExtra<data>("info_key")

        Picasso.get().load(item?.urlToImage).fit().placeholder(R.color.black).error(R.color.black).into(ImageFilm)

        TitleFilm.text = "" + item?.title
        DescriptionFilm.text = "" + item?.overview
        ReleaseDateFilm.text = "" + item?.releaseDate
        RatingFilm.text = "" + item?.voteVerage
    }
}