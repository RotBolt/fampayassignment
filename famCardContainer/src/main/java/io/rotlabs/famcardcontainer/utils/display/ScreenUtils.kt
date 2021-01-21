package io.rotlabs.famcardcontainer.utils.display

import android.content.Context
import android.content.res.Resources
import androidx.annotation.DimenRes

object ScreenUtils {

    fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

    fun getDimension(@DimenRes dimenId: Int, context: Context) =
        context.resources.getDimension(dimenId)
}