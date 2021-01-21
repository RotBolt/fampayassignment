package io.rotlabs.famcardcontainer.ui.cardgroups.cards.imageCard

import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginEnd
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import io.rotlabs.famcardcontainer.utils.WHITE_HEX

class ImageCardViewHolder(parent: ViewGroup) :
    BaseViewHolder<Card>(parent, R.layout.item_image_card) {
    override fun setupView(view: View) {

    }

    override fun bind(data: Card) {
        with(CardDisplayUtils) {
            setUrlAction(itemView, data.url)
            setBackgroundColor(itemView, data.bgColor ?: WHITE_HEX)
            setBackgroundGradient(itemView, data.bgGradient, 8)
            data.bgImage?.let { bgImage ->
                setViewToAspectRatio(itemView, bgImage.aspectRatio, itemView.marginEnd)
                setBackgroundImage(itemView, bgImage, 8, itemView.marginEnd)
            }
        }
    }
}