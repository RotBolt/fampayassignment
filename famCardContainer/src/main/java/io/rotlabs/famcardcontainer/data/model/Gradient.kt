package io.rotlabs.famcardcontainer.data.model

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.google.gson.annotations.SerializedName

data class Gradient(
    @SerializedName("colors")
    val colors: List<String>,

    @SerializedName("angle")
    val angle: Int
)