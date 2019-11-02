package br.com.cubos.cinemacubos.entries

import java.io.Serializable

data class Companies(
    val logo_path: String,
    val name: String,
    val origin_country: String
) : Serializable