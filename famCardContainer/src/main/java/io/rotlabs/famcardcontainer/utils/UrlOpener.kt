package io.rotlabs.famcardcontainer.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object UrlOpener {
    @JvmStatic
    fun openUrl(url: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}