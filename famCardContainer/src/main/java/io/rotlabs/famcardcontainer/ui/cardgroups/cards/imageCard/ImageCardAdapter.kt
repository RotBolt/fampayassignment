package io.rotlabs.famcardcontainer.ui.cardgroups.cards.imageCard

import android.view.ViewGroup
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseAdapter

class ImageCardAdapter(
    private val dataList: ArrayList<Card>,
    private val spanCount: Int
) :
    BaseAdapter<Card, ImageCardViewHolder>(dataList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCardViewHolder {
        return ImageCardViewHolder(parent, spanCount)
    }

}