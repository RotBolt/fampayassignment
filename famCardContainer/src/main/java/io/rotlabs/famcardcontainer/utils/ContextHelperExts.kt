package io.rotlabs.famcardcontainer.utils

import android.content.Context
import android.content.SharedPreferences


fun Context.getAppSharedPrefs(): SharedPreferences {
    return getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE)
}