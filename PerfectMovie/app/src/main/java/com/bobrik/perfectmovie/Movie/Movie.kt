package com.bobrik.perfectmovie.Movie

import com.bobrik.perfectmovie.Interfaces.MovieAbstractor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class Movie(url : String, Top : Boolean) : MovieAbstractor {

    override var listTitles: MutableList<String> = mutableListOf<String>()
    override var listDescriptions: MutableList<String> = mutableListOf<String>()
    override var listUrlsOfImage: MutableList<String> = mutableListOf<String>()
    override var listRatings: MutableList<String> = mutableListOf<String>()
    override var listReleaseDates: MutableList<String> = mutableListOf<String>()
    override var countFilms: Int = 0
    override var jsonurl: String = url

    init{
        if(Top) {
            getInfoTop()
        }
        else{
            getInfoRating()
        }
    }

    override fun refreshRating() {
        clearList()
        getInfoRating()
    }

    override fun refreshTop() {
        clearList()
        getInfoTop()
    }

    override fun clearList() {
        listDescriptions.clear()
        listRatings.clear()
        listTitles.clear()
        listUrlsOfImage.clear()
        listReleaseDates.clear()

        countFilms = 0
    }

    override fun getInfoRating() {
        val httpClient : OkHttpClient = OkHttpClient().newBuilder().connectTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).readTimeout(300, TimeUnit.SECONDS).build()
        val request : Request = Request.Builder().url(jsonurl).build()
        val response : Response = httpClient.newCall(request).execute()
        var json : String = ""

        clearList()

        if(response.isSuccessful) {
            json = response.body!!.string()
        }
        else {
            return
        }

        val count : Int = (JSONObject(json).getJSONArray("results").length())

        for(count2 in 1..count)
        {
            listDescriptions.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("overview").toString())
            listReleaseDates.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("release_date").toString())
            listUrlsOfImage.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("poster_path").toString())
            listTitles.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("title").toString())
            listRatings.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("vote_average").toString())
        }
        countFilms = count
    }

    override fun getInfoTop() {
        val httpClient : OkHttpClient = OkHttpClient()
        val request : Request = Request.Builder().url(jsonurl).build()
        val response : Response = httpClient.newCall(request).execute()
        var json : String = ""

        clearList()

        if(response.isSuccessful) {
            json = response.body!!.string()
        }
        else {
            return
        }

        val count : Int = (JSONObject(json).getJSONArray("results").length())

        for(count2 in 1..count)
        {
            listDescriptions.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("overview").toString())
            listReleaseDates.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("release_date").toString())
            listUrlsOfImage.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("poster_path").toString())
            listTitles.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("title").toString())
            listRatings.add((JSONObject(json).getJSONArray("results").getJSONObject(count2 - 1)).get("vote_average").toString())
        }
        countFilms = count
    }

    override fun getTitle(index: Int): String {
        return listTitles.get(index)
    }

    override fun getDescription(index: Int): String {
        return listDescriptions.get(index)
    }

    override fun getUrlToImage(index: Int): String {
        return listUrlsOfImage.get(index)
    }

    override fun getReleaseDate(index: Int): String {
        return listReleaseDates.get(index)
    }

    override fun getRating(index: Int): String {
        return listRatings.get(index)
    }

    override fun getCount(): Int {
        return countFilms
    }
}