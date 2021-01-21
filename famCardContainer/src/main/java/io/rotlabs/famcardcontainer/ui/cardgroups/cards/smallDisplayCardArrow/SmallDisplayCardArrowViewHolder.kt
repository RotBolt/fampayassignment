package io.rotlabs.famcardcontainer.ui.cardgroups.cards.smallDisplayCardArrow

import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.scaleMatrix
import androidx.core.view.isVisible
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import io.rotlabs.famcardcontainer.utils.WHITE_HEX
import io.rotlabs.famcardcontainer.utils.display.ScreenUtils
import kotlinx.android.synthetic.main.item_small_display_card_arrow.view.*

class SmallDisplayCardArrowViewHolder(
    parent: ViewGroup,
    private val spanCount: Int
) :
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

        CardDisplayUtils.setBackgroundColor(itemView, data.bgColor ?: WHITE_HEX)

        val roundedCorners =
            ScreenUtils.getDimension(R.dimen.measure_8_dp, itemView.context)

        CardDisplayUtils.setBackgroundGradient(itemView, data.bgGradient, roundedCorners)

        CardDisplayUtils.setBackgroundImage(
            itemView,
            data.bgImage,
            roundedCorners.toInt(),
            itemView.marginEnd,
            spanCount = spanCount
        )
    }
}