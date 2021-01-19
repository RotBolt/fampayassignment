package io.rotlabs.famcardcontainer.data.model

import com.google.gson.annotations.SerializedName

enum class DesignType(value :String) {
    @SerializedName("HC1")
    SMALL_DISPLAY_CARD("HC1"),

    @SerializedName("HC3")
    BIG_DISPLAY_CARD("HC3"),

    @SerializedName("HC5")
    IMAGE_CARD("HC5"),

    @SerializedName("HC6")
    SMALL_CARD_WITH_ARROW("HC6"),

    @SerializedName("HC9")
    DYNAMIC_WIDTH_CARD("HC9")
}