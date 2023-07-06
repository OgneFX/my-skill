package com.first.acteleven

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

private const val PREFERENCE_NAME = "prefz_name"
private const val KEY_FOR_EDITOR = "keys_editor"



class Repository(context: Context) {
    var localValue:String = ""
     private val prefs: SharedPreferences by lazy {
         context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
     }

    fun getText(context: Context) : String {
        return when {
            getDataFromSharedPreference(context)!=null -> getDataFromSharedPreference(context)!!
            getDataFromLocalVariable()!=null -> getDataFromLocalVariable()!!

            else -> "no source here"
        }

    }

    fun saveText (text:String)
    {
        prefs.edit().putString(KEY_FOR_EDITOR, text).apply()
        localValue = text

    }

    fun clearText() {

        prefs.edit().remove(KEY_FOR_EDITOR).apply()
        localValue = ""

    }


     private fun getDataFromSharedPreference(context: Context): String?
    {

        return prefs.getString(KEY_FOR_EDITOR, context.toString())
    }


    private fun getDataFromLocalVariable(): String?
    {

        return localValue
    }






}