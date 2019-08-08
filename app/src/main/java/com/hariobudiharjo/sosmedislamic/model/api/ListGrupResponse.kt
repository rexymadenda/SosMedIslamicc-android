package com.hariobudiharjo.sosmedislamic.model.api

data class ListGrupResponse(
    val status: Boolean? = null,
    val message: String? = null,
    val data: Array<Datum>? = null
) {
    data class Datum(
        val id: String? = null,
        val nama: String? = null,
        val deskripsi: String? = null
    )
}

