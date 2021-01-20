package io.rotlabs.famcardcontainer.ui.cardgroups

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.data.model.CardGroup
import io.rotlabs.famcardcontainer.data.model.DesignType
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.ui.cardgroups.cards.bigDisplayCard.BigDisplayCardAdapter
import io.rotlabs.famcardcontainer.ui.cardgroups.cards.dynamicWidthCard.DynamicWidthDisplayCardAdapter
import io.rotlabs.famcardcontainer.ui.cardgroups.cards.imageCard.ImageCardAdapter
import io.rotlabs.famcardcontainer.ui.cardgroups.cards.smallDisplayCard.SmallDisplayCardAdapter
import io.rotlabs.famcardcontainer.ui.cardgroups.cards.smallDisplayCardArrow.SmallDisplayCardArrowAdapter
import kotlinx.android.synthetic.main.item_cards_list.view.*


// contains cards list of differnt design types
class CardGroupViewHolder(parent: ViewGroup) :
    BaseViewHolder<CardGroup>(parent, R.layout.item_cards_list) {

    override fun setupView(view: View) {

    }

    override fun bind(data: CardGroup) {
        bindList(data, itemView, data.isScrollable)
    }

    private fun bindList(data: CardGroup, view: View, isScrollable: Boolean) {

        itemView.rvCardsHolder.isVisible = true
        val cardsList = arrayListOf<Card>()
        cardsList.addAll(data.cards)

        if (isScrollable) {
            view.rvCardsHolder.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        } else {
            view.rvCardsHolder.layoutManager = GridLayoutManager(view.context, cardsList.size)
        }
        when (data.designType) {
            DesignType.SMALL_DISPLAY_CARD -> {
                view.rvCardsHolder.adapter = SmallDisplayCardAdapter(cardsList)
            }
            DesignType.SMALL_CARD_WITH_ARROW -> {
                view.rvCardsHolder.adapter = SmallDisplayCardArrowAdapter(cardsList)
            }
            DesignType.IMAGE_CARD -> {
                view.rvCardsHolder.adapter = ImageCardAdapter(cardsList)
            }
            DesignType.BIG_DISPLAY_CARD -> {
                view.rvCardsHolder.adapter = BigDisplayCardAdapter(cardsList)
            }
            DesignType.DYNAMIC_WIDTH_CARD -> {
                view.rvCardsHolder.adapter = DynamicWidthDisplayCardAdapter(cardsList)
            }
        }
    }


}