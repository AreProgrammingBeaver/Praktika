package com.bobrik.perfectmovie.Interfaces

interface MovieAbstractor {
    var listTitles : MutableList<String>
    var listDescriptions : MutableList<String>
    var listUrlsOfImage : MutableList<String>
    var listRatings : MutableList<String>
    var listReleaseDates : MutableList<String>
    var countFilms : Int
    var jsonurl : String

    fun refreshRating()
    fun refreshTop()
    fun clearList()
    fun getInfoRating()
    fun getInfoTop()
    fun getTitle(index : Int) : String
    fun getDescription(index : Int) : String
    fun getUrlToImage(index : Int) : String
    fun getReleaseDate(index : Int) : String
    fun getRating(index : Int) : String
    fun getCount() : Int
}