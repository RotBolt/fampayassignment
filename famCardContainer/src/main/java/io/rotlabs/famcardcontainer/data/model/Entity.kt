package io.rotlabs.famcardcontainer.data.model

import com.google.gson.annotations.SerializedName

data class Entity(
    @SerializedName("text")
    val text:String,

    @SerializedName("color")
    val color:String? = null,

    @SerializedName("url")
    val url:String? = null,

    @SerializedName("font_style")
    val fontStyle:String? = null
)