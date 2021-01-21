package io.rotlabs.famcardcontainer.ui.cardgroups

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.local.RemovedCardsDetailsHolder
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


// contains cards list of different design types
class CardGroupViewHolder(
    parent: ViewGroup,
    private val removedCardsDetailsHolder: RemovedCardsDetailsHolder
) :
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

        val spanCount = if (isScrollable || cardsList.isEmpty()) 1 else cardsList.size

        when (data.designType) {
            DesignType.SMALL_DISPLAY_CARD -> {
                view.rvCardsHolder.adapter = SmallDisplayCardAdapter(cardsList, spanCount)
            }
            DesignType.SMALL_CARD_WITH_ARROW -> {
                view.rvCardsHolder.adapter = SmallDisplayCardArrowAdapter(cardsList, spanCount)
            }
            DesignType.IMAGE_CARD -> {
                view.rvCardsHolder.adapter = ImageCardAdapter(cardsList, spanCount)
            }
            DesignType.BIG_DISPLAY_CARD -> {

                val removedCardList = removedCardsDetailsHolder.getRemovedCardsList()

                val filteredCardList = cardsList.filter {
                    !removedCardList.contains(it.name)
                }

                val toShowCardsList = arrayListOf<Card>()
                toShowCardsList.addAll(filteredCardList)

                val hc3spanCount =
                    if (isScrollable || toShowCardsList.isEmpty()) 1 else toShowCardsList.size

                if (!isScrollable) {
                    view.rvCardsHolder.layoutManager =
                        GridLayoutManager(view.context, hc3spanCount)
                }

                view.rvCardsHolder.adapter =
                    BigDisplayCardAdapter(toShowCardsList, removedCardsDetailsHolder, hc3spanCount)
            }
            DesignType.DYNAMIC_WIDTH_CARD -> {
                data.height?.let { cardHeight ->
                    val cardHeightPairedList = cardsList.map {
                        cardHeight to it
                    }
                    val arrayList = arrayListOf<Pair<Int, Card>>()
                    arrayList.addAll(cardHeightPairedList)

                    view.rvCardsHolder.adapter = DynamicWidthDisplayCardAdapter(arrayList)
                }
            }
        }
    }


}