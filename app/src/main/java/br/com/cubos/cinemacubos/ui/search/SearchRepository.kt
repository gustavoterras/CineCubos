package br.com.cubos.cinemacubos.ui.search

import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single

interface SearchRepository {
    fun searchRemote(keyword: String): Single<Response>
}
