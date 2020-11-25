package com.bobrik.perfectmovie.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class data(val title : String, val urlToImage : String, val voteVerage : String, val releaseDate : String, val overview : String) : Parcelable {
}