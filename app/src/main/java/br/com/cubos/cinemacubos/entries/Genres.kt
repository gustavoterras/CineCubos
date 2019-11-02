package br.com.cubos.cinemacubos.entries

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genres(
    var id: Int,
    var name: String
) : Parcelable