package io.rotlabs.famcardcontainer.ui.cardgroups.cards.bigDisplayCard

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.local.RemovedCardsDetailsHolder
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter

class BigDisplayCardAdapter(
    private val dataList: ArrayList<Card>,
    private val removedCardsDetailsHolder: RemovedCardsDetailsHolder,
    private val spanCount: Int
) :
    BaseAdapter<Card, BigDisplayCardViewHolder>(dataList) {

    private val actionButtonClickListener = object : OnActionButtonClickListener {
        override fun onRemindLaterClick(position: Int) {
            val item = dataList[position]
            removedCardsDetailsHolder.addToRemindLaterList(item.name)
            dataList.remove(item)
            notifyItemRemoved(position)
        }

        override fun onDismissClick(position: Int) {
            val item = dataList[position]
            removedCardsDetailsHolder.addToDismissList(item.name)
            dataList.remove(item)
            notifyItemRemoved(position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigDisplayCardViewHolder {
        return BigDisplayCardViewHolder(parent, actionButtonClickListener, spanCount)
    }

}