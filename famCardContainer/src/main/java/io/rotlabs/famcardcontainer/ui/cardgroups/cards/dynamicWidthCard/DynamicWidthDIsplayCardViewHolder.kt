package io.rotlabs.famcardcontainer.ui.cardgroups.cards.dynamicWidthCard

import android.view.View
import android.view.ViewGroup
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import io.rotlabs.famcardcontainer.utils.display.WHITE_HEX
import io.rotlabs.famcardcontainer.utils.display.dp
import kotlinx.android.synthetic.main.item_dynamic_width_card.view.*

class DynamicWidthDIsplayCardViewHolder(parent: ViewGroup) :
    BaseViewHolder<Pair<Int, Card>>(parent, R.layout.item_dynamic_width_card) {
    override fun setupView(view: View) {

    }

    override fun bind(data: Pair<Int, Card>) {
        with(CardDisplayUtils) {
            setViewToAspectRatioHC9(itemView, 1.0, data.first.dp)
            setBackgroundColor(itemView, data.second.bgColor ?: WHITE_HEX)
            setBackgroundGradient(itemView, data.second.bgGradient)

            data.second.bgImage?.let { bgImage ->
                setViewToAspectRatioHC9(itemView, bgImage.aspectRatio, data.first.dp)
                setBackgroundImage(itemView.ivBgImage, bgImage, 8)
            }

            setUrlAction(itemView, data.second.url)
        }
    }
}