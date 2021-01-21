package io.rotlabs.famcardcontainer.ui.cardgroups.cards.bigDisplayCard

import android.animation.Animator
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
import io.rotlabs.famcardcontainer.utils.WHITE_HEX
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import io.rotlabs.famcardcontainer.utils.display.ScreenUtils
import kotlinx.android.synthetic.main.item_big_display_card.view.*

class BigDisplayCardViewHolder(
    parent: ViewGroup,
    private val actionButtonClickListener: OnActionButtonClickListener,
    private val spanCount: Int
) :
    BaseViewHolder<Card>(parent, R.layout.item_big_display_card) {

    private var urlActionString: String? = null
    private var slide: Boolean = true

    override fun setupView(view: View) {

        itemView.btnRemindLater.setOnClickListener {
            actionButtonClickListener.onRemindLaterClick(adapterPosition)
        }

        itemView.btnDismiss.setOnClickListener {
            actionButtonClickListener.onDismissClick(adapterPosition)
        }

        setupSlideOnCard(view.bigCardContainer)
    }

    override fun bind(data: Card) {

        with(CardDisplayUtils) {
            val scaleFactor = 1.0f / spanCount
            resizeView(itemView.btnDismiss, scaleFactor)
            resizeView(itemView.btnRemindLater, scaleFactor)
            resizeView(itemView.tvBigTitle, scaleFactor)
            resizeView(itemView.tvDescription, scaleFactor)


            setTextToView(itemView.tvBigTitle, data.formattedTitle, data.title)

            if (data.description != null) {
                setTextToView(itemView.tvDescription, data.formattedDescription, data.description)
            } else {
                itemView.tvDescription.isVisible = false
            }

            urlActionString = data.url

            setUrlAction(itemView.bigCardContainer, data.url)

            setBackgroundColor(itemView.bigCardContainer, data.bgColor ?: WHITE_HEX)
            setBackgroundGradient(itemView.bigCardContainer, data.bgGradient)

            data.bgImage?.let { bgImage ->
                setViewToAspectRatio(itemView, bgImage.aspectRatio, itemView.marginEnd, spanCount)

                val roundedCorners =
                    ScreenUtils.getDimension(R.dimen.measure_8_dp, itemView.context).toInt()

                setBackgroundImage(
                    itemView.bigCardContainer,
                    bgImage,
                    roundedCorners,
                    itemView.marginEnd,
                    spanCount = spanCount
                )
            }

            data.cta.forEach { cta ->
                setupCta(itemView, cta, scaleFactor)
            }

        }
    }

    private fun setupCta(view: View, cta: CallToAction, scaleFactor: Float) {
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

        CardDisplayUtils.resizeView(ctaButton, scaleFactor)

        view.ctaHolder.addView(ctaButton)

    }

    private fun getSlidePosition(): Float {
        return (itemView.btnRemindLater.x + itemView.btnRemindLater.width + itemView.btnRemindLater.marginStart)
    }


    private fun setupSlideOnCard(view: View) {
        view.setOnLongClickListener {
            if (slide) {
                val position = getSlidePosition()
                ObjectAnimator.ofFloat(view, "translationX", position).apply {
                    duration = 500
                    start()
                }
                slide = !slide
                handleBigCardClick(view, urlActionString)
            }
            true
        }
    }

    private fun handleBigCardClick(view: View, urlActionString: String?) {
        view.isClickable = slide
        if (!slide) {
            view.setOnClickListener {
                ObjectAnimator.ofFloat(view, "translationX", 0f).apply {
                    duration = 500
                    addListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {

                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            slide = !slide
                            handleBigCardClick(view, urlActionString)
                        }

                        override fun onAnimationCancel(animation: Animator?) {

                        }

                        override fun onAnimationRepeat(animation: Animator?) {

                        }

                    })
                    start()
                }
            }
        } else {
            CardDisplayUtils.setUrlAction(view, urlActionString)
        }
    }
}