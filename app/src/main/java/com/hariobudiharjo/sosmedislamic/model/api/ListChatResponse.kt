package com.hariobudiharjo.sosmedislamic.model.api

data class ListChatResponse(
    val status: Boolean? = null,
    val message: String? = null,
    val data: List<Datum>? = null
) {
    data class Datum(
        val id: String? = null,
        val uid: String? = null,
        val gid: String? = null,
        val message: String? = null,
        val waktu: String? = null,
        val status: String? = null
    )
}

