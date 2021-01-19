package io.rotlabs.famcardcontainer.data.model

import com.google.gson.annotations.SerializedName

data class CallToAction(
    @SerializedName("text")
    val text:String,

    @SerializedName("bg_color")
    val bgColor:String? = null,

    @SerializedName("url")
    val url:String? = null,

    @SerializedName("text_color")
    val textColor:String? = null
)