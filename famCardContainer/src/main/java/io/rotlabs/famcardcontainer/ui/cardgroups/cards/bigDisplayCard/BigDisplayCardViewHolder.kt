package io.rotlabs.famcardcontainer.ui.cardgroups.cards.bigDisplayCard

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.marginEnd
import com.google.android.material.button.MaterialButton
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.CallToAction
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.utils.UrlOpener
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import io.rotlabs.famcardcontainer.utils.display.WHITE_HEX
import kotlinx.android.synthetic.main.item_big_display_card.view.*

class BigDisplayCardViewHolder(parent: ViewGroup) :
    BaseViewHolder<Card>(parent, R.layout.item_big_display_card) {
    override fun setupView(view: View) {

    }

    override fun bind(data: Card) {
        with(CardDisplayUtils) {
            setTextToView(itemView.tvBigTitle, data.formattedTitle, data.title)

            if (data.description != null) {
                setTextToView(itemView.tvDescription, data.formattedDescription, data.description)
            } else {
                itemView.tvDescription.isVisible = false
            }
            setUrlAction(itemView, data.url)

            setBackgroundColor(itemView, data.bgColor ?: WHITE_HEX)
            setBackgroundGradient(itemView, data.bgGradient)

            data.bgImage?.let { bgImage ->
                setViewToAspectRatio(itemView, bgImage.aspectRatio, itemView.marginEnd)
                setBackgroundImage(itemView, bgImage, 8, itemView.marginEnd)
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
}