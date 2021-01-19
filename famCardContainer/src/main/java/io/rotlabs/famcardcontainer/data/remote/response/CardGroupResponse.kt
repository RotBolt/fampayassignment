package io.rotlabs.famcardcontainer.data.remote.response

import com.google.gson.annotations.SerializedName
import io.rotlabs.famcardcontainer.data.model.CardGroup

data class CardGroupResponse(
    @SerializedName("card_groups")
    val cardGroups:List<CardGroup>
)