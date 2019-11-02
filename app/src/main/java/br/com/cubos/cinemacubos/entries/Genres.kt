package br.com.cubos.cinemacubos.entries

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.cubos.cinemacubos.BR
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genres(
    var id: Int,
    var name: String,
    private var _selected: Boolean
) : Parcelable, BaseObservable() {

    @IgnoredOnParcel
    @get:Bindable
    var selected
        get() = _selected
        set(value) {
            _selected = value
            notifyPropertyChanged(BR.selected)
        }
}
