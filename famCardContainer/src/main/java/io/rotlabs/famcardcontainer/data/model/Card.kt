package io.rotlabs.famcardcontainer.data.model

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName(value = "name")
    val name: String,

    @SerializedName(value = "formatted_title")
    val formattedTitle:FormattedText?,

    @SerializedName(value = "title")
    val title:String,

    @SerializedName(value = "formatted_description")
    val formattedDescription:FormattedText?,

    @SerializedName(value = "description")
    val description:String? = null,

    @SerializedName(value = "icon")
    val icon:CardImage? = null,

    @SerializedName(value = "url")
    val url:String? = null,

    @SerializedName(value = "bg_image")
    val bgImage:CardImage? = null,

    @SerializedName(value = "bg_color")
    val bgColor:String?= null,

    @SerializedName(value = "bg_gradient")
    val bgGradient: Gradient? = null,

    @SerializedName(value = "cta")
    val cta:List<CallToAction>

)