package io.rotlabs.famcardcontainer.ui.cardgroups.cards.bigDisplayCard

import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import com.google.android.material.button.MaterialButton
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.CallToAction
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.utils.UrlOpener
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import io.rotlabs.famcardcontainer.utils.WHITE_HEX
import kotlinx.android.synthetic.main.item_big_display_card.view.*

class BigDisplayCardViewHolder(
    parent: ViewGroup,
    private val actionButtonClickListener: OnActionButtonClickListener
) :
    BaseViewHolder<Card>(parent, R.layout.item_big_display_card) {
    override fun setupView(view: View) {

        itemView.btnRemindLater.setOnClickListener {
            actionButtonClickListener.onRemindLaterClick(adapterPosition)
        }

        itemView.btnDismiss.setOnClickListener {
            actionButtonClickListener.onDismissClick(adapterPosition)
        }

        setupSlideOnCard(view)
    }

    override fun bind(data: Card) {
        with(CardDisplayUtils) {
            setTextToView(itemView.tvBigTitle, data.formattedTitle, data.title)

            if (data.description != null) {
                setTextToView(itemView.tvDescription, data.formattedDescription, data.description)
            } else {
                itemView.tvDescription.isVisible = false
            }
            setUrlAction(itemView.bigCardContainer, data.url)

            setBackgroundColor(itemView.bigCardContainer, data.bgColor ?: WHITE_HEX)
            setBackgroundGradient(itemView.bigCardContainer, data.bgGradient)

            data.bgImage?.let { bgImage ->
                setViewToAspectRatio(itemView, bgImage.aspectRatio, itemView.marginEnd)
                setBackgroundImage(itemView.bigCardContainer, bgImage, 8, itemView.marginEnd)
            }

            data.cta.forEach { cta ->
                setupCta(itemView, cta)
            }

        }
    }

    private fun setupCta(view: View, cta: CallToAction) {
        val ctaButton = LayoutInflater.from(view.context)
            .inflate(R.layout.layout_cta, null, false) as MaterialButton

        ctaButton.text = cta.text
        cta.textColor?.let { colorHex ->
            try {
                ctaButton.setTextColor(Color.parseColor(colorHex))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        cta.bgColor?.let { bgColor ->
            try {
                ctaButton.setBackgroundColor(Color.parseColor(bgColor))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        cta.url?.let { url ->
            ctaButton.setOnClickListener {
                UrlOpener.openUrl(url, view.context)
            }
        }

        view.ctaHolder.addView(ctaButton)

    }

    private fun getSlidePosition(): Float {
        return itemView.btnRemindLater.x + itemView.btnRemindLater.width + itemView.btnRemindLater.marginStart
    }


    private fun setupSlideOnCard(view: View) {
        var slide = true
        view.bigCardContainer.setOnLongClickListener {
            val position = getSlidePosition()
            val value = if (slide) position else 0f
            ObjectAnimator.ofFloat(view.bigCardContainer, "translationX", value).apply {
                duration = 500
                start()
            }
            slide = !slide
            true
        }
    }
}