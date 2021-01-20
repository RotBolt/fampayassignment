package io.rotlabs.famcardcontainer.utils

import io.rotlabs.famcardcontainer.data.remote.response.CardGroupResponse

interface OnSuccessResponse {
    fun onSuccess(cardGroupResponse: CardGroupResponse)
}