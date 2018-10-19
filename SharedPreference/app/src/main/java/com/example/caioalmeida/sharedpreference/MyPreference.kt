package com.example.caioalmeida.sharedpreference

import android.content.Context

class MyPreference(context: Context){

    val PREFERENCE_LOGIN = "isLoged"

    val isLoged = context.getSharedPreferences(PREFERENCE_LOGIN, Context.MODE_PRIVATE)

    fun getIfLoged () : Boolean{
        return isLoged.getBoolean(PREFERENCE_LOGIN , false)
    }

    fun setLoged() {
        isLoged.edit().putBoolean(PREFERENCE_LOGIN, true).commit()
    }

    fun setLogout(){
        isLoged.edit().putBoolean(PREFERENCE_LOGIN, false).commit()
    }

}