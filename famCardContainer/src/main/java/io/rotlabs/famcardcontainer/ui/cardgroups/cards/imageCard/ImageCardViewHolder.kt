package io.rotlabs.famcardcontainer.ui.cardgroups.cards.imageCard

import android.view.View
import android.view.ViewGroup
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import kotlinx.android.synthetic.main.item_image_card.view.*

class ImageCardViewHolder(parent: ViewGroup) :
    BaseViewHolder<Card>(parent, R.layout.item_image_card) {
    override fun setupView(view: View) {

    }

    override fun bind(data: Card) {
        with(CardDisplayUtils) {
            setUrlAction(itemView, data.url)
            setBackgroundColor(itemView, data.bgColor)
            setBackgroundGradient(itemView, data.bgGradient)
            setBackgroundImage(itemView.ivCardImage, data.bgImage, 8)
        }
    }
}