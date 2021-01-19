package io.rotlabs.famcardcontainer.data.model

import com.google.gson.annotations.SerializedName

data class CardGroup(
    @SerializedName("name")
    val name:String,

    @SerializedName("design_type")
    val designTypes:DesignType,

    @SerializedName("cards")
    val cards:List<Card>,

    @SerializedName("height")
    val height:Int? = null,

    @SerializedName("is_scrollable")
    val isScrollable:Boolean
)