package com.hariobudiharjo.sosmedislamic.model.api

data class ListMemberResponse (
    val status: Boolean? = null,
    val message: String? = null,
    val data: List<Datum>? = null
){
    data class Datum (
        val uid: String? = null,
        val gid: String? = null,
        val status: String? = null
    )
}


