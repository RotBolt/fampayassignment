package io.rotlabs.famcardcontainer.data.local

import android.content.SharedPreferences

class RemovedCardsDetailsHolder(private val sharedPrefs: SharedPreferences) {

    private val remindLaterList: MutableList<String> = mutableListOf()

    fun addToRemindLaterList(cardName: String) {
        remindLaterList.add(cardName)
    }


    fun addToDismissList(cardName: String) {
        val dismissList = getDismissList().toMutableList()
        dismissList.add(cardName)
        val newListString = dismissList.joinToString(",")
        sharedPrefs.edit().putString(PrefKeys.PREF_KEY_DISMISS_LIST, newListString).apply()
    }

    fun getRemovedCardsList(): List<String> {
        val dismissList = getDismissList()
        return remindLaterList + dismissList
    }

    private fun getDismissList(): List<String> {
        val listString = sharedPrefs.getString(PrefKeys.PREF_KEY_DISMISS_LIST, "") ?: ""
        return listString.split(",")
    }

}