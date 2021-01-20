package io.rotlabs.famcardcontainer.ui.cardgroups.cards.dynamicWidthCard

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter

class DynamicWidthDisplayCardAdapter(private val dataList: ArrayList<Pair<Int, Card>>) :
    BaseAdapter<Pair<Int, Card>, DynamicWidthDIsplayCardViewHolder>(dataList) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DynamicWidthDIsplayCardViewHolder {
        return DynamicWidthDIsplayCardViewHolder(parent)
    }
}