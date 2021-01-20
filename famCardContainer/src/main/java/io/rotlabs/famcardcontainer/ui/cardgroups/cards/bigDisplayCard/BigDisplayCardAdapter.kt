package io.rotlabs.famcardcontainer.ui.cardgroups.cards.bigDisplayCard

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter

class BigDisplayCardAdapter(private val dataList: ArrayList<Card>) :
    BaseAdapter<Card, BigDisplayCardViewHolder>(dataList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigDisplayCardViewHolder {
        return BigDisplayCardViewHolder(parent)
    }

}