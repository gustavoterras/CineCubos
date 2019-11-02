package br.com.cubos.cinemacubos.entries

import java.io.Serializable

data class Movie(
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val release_date: String,
    val homepage: String,
    val production_companies: ArrayList<Companies>
) : Serializable {

    fun shortName(): String {
        return title.split(":").firstOrNull() ?: title
    }

}