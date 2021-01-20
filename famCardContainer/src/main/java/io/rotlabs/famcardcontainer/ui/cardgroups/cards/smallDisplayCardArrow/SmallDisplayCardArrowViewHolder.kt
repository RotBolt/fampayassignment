package io.rotlabs.famcardcontainer.ui.cardgroups.cards.smallDisplayCardArrow

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import kotlinx.android.synthetic.main.item_small_display_card.view.*

class SmallDisplayCardArrowViewHolder(parent: ViewGroup) :
    BaseViewHolder<Card>(parent, R.layout.item_small_display_card_arrow) {

    companion object {
        const val TAG = "SmallDisplayCardHolderArrow"
    }

    override fun setupView(view: View) {

    }

    override fun bind(data: Card) {
        CardDisplayUtils.setTextToView(itemView.tvTitle, data.formattedTitle, data.title)

        if (data.description != null) {
            CardDisplayUtils.setTextToView(
                itemView.tvDescription,
                data.formattedDescription,
                data.description
            )
        } else {
            itemView.tvDescription.isVisible = false
        }

        CardDisplayUtils.setIcon(itemView.ivIcon, data.icon)

        CardDisplayUtils.setUrlAction(itemView, data.url)

        CardDisplayUtils.setBackgroundColor(itemView, data.bgColor)
        CardDisplayUtils.setBackgroundGradient(itemView, data.bgGradient)
        CardDisplayUtils.setBackgroundImage(itemView, data.bgImage,8)
    }
}