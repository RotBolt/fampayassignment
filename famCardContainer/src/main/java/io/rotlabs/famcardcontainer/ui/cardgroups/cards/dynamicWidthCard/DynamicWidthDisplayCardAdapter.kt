package io.rotlabs.famcardcontainer.ui.cardgroups.cards.dynamicWidthCard

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter

class DynamicWidthDisplayCardAdapter(private val dataList: ArrayList<Card>) :
    BaseAdapter<Card, DynamicWidthCardViewHolder>(dataList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DynamicWidthCardViewHolder {
        return DynamicWidthCardViewHolder(parent)
    }
}