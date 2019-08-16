package com.hariobudiharjo.sosmedislamic.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SPManager(context: Context) {
    val SP_APP = "spPPIF"
    val SP_SUDAH_LOGIN = "spLogin"
    val SP_GID = "gid"
    val SP_UID = "uid"
    lateinit var sp: SharedPreferences
    lateinit var spEditor: SharedPreferences.Editor

    init {
        sp = context.getSharedPreferences(SP_APP, Context.MODE_PRIVATE)
        spEditor = sp.edit()
    }

    fun saveSPString(keySP: String, value: String) {
        spEditor.putString(keySP, value)
        spEditor.commit()
    }

    fun saveSPBoolean(keySP: String, value: Boolean) {
        spEditor.putBoolean(keySP, value)
        spEditor.commit()
    }

    fun saveSPUid(value: String) {
        saveSPString(SP_UID, value)
    }

    fun saveSPSudahLogin(value: Boolean) {
        saveSPBoolean(SP_SUDAH_LOGIN, value)
    }

    fun saveSPGid(value: String) {
        saveSPString(SP_GID, value)
    }

    fun saveSPEmail(value: String) {
        saveSPString("EMAIL", value)
    }
    fun saveSPNama(value: String) {
        saveSPString("NAMA", value)
    }

    fun getSPSudahLogin(): Boolean? {
        return sp.getBoolean(SP_SUDAH_LOGIN, false)
    }

    fun getSPUid(): String? {
        return sp.getString(SP_UID, null)
    }

    fun getSPGid(): String? {
        return sp.getString(SP_GID, null)
    }
    fun getSPEmail(): String? {
        return sp.getString("EMAIL", null)
    }
    fun getSPNama(): String? {
        return sp.getString("NAMA", null)
    }

}