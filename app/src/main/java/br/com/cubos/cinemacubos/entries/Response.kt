package br.com.cubos.cinemacubos.entries

data class Response(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
)