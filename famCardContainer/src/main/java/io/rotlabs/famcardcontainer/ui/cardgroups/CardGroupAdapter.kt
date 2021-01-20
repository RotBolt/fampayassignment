package io.rotlabs.famcardcontainer.ui.cardgroups

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.model.CardGroup
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter

class CardGroupAdapter(private val dataList: ArrayList<CardGroup>) :
    BaseAdapter<CardGroup, CardGroupViewHolder>(dataList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardGroupViewHolder {
        return CardGroupViewHolder(parent)
    }
}