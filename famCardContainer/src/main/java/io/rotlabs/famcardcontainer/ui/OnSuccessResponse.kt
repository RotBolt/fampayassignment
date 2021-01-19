package io.rotlabs.famcardcontainer.ui

import io.rotlabs.famcardcontainer.data.remote.response.CardGroupResponse

interface OnSuccessResponse {
    fun onSuccess(cardGroupResponse: CardGroupResponse)
}