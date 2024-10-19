package com.example.m11_timer_data_storage

import android.content.Context

private const val KEY_STRING_NAME = "Key1"
private const val PREFERENCE_NAME = "prefs_name"

class Repository(context: Context) {

    private var localValue: String? = null
    private val prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = prefs.edit()


    private fun getDataFromSharedPreference(): String? = prefs.getString(KEY_STRING_NAME, null)


    private fun getDataFromLocalVariable(): String? = localValue

    fun saveText(text: String) {
        editor.putString(KEY_STRING_NAME, text).apply()
        localValue = text
    }

    fun clearText(){
        editor.clear().apply()
        localValue = null
    }

    fun getText(): String = when {
        getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
        getDataFromSharedPreference() != null -> getDataFromSharedPreference()!!
        else -> ""
    }
}