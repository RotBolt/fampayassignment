package io.rotlabs.famcardcontainer.ui.cardgroups.cards.smallDisplayCard

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter

class SmallDisplayCardAdapter(
    private val dataList: ArrayList<Card>,
    private val spanCount: Int
) :
    BaseAdapter<Card, SmallDisplayCardViewHolder>(dataList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallDisplayCardViewHolder {
        return SmallDisplayCardViewHolder(parent, spanCount)
    }
}