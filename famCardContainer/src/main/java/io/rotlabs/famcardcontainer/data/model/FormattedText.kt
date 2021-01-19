package io.rotlabs.famcardcontainer.data.model

import com.google.gson.annotations.SerializedName

data class FormattedText(

    @SerializedName("text")
    val text:String,

    @SerializedName("entities")
    val entities:List<Entity>
)