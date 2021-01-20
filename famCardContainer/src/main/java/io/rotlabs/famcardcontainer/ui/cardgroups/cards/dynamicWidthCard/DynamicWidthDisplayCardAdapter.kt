package io.rotlabs.famcardcontainer.ui.cardgroups.cards.dynamicWidthCard

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter

class DynamicWidthDisplayCardAdapter(private val dataList: ArrayList<Pair<Int, Card>>) :
    BaseAdapter<Pair<Int, Card>, DynamicWidthDisplayCardViewHolder>(dataList) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DynamicWidthDisplayCardViewHolder {
        return DynamicWidthDisplayCardViewHolder(parent)
    }
}