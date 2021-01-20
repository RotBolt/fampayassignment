package io.rotlabs.famcardcontainer.ui.cardgroups.cards.smallDisplayCardArrow

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder

class SmallDisplayCardArrowAdapter(private val dataList: ArrayList<Card>) :
    BaseAdapter<Card, SmallDisplayCardArrowViewHolder>(dataList) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SmallDisplayCardArrowViewHolder {
        return SmallDisplayCardArrowViewHolder(parent)
    }


}