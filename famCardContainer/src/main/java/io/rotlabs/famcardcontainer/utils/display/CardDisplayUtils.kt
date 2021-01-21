package io.rotlabs.famcardcontainer.utils.display

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginStart
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.CardImage
import io.rotlabs.famcardcontainer.data.model.FormattedText
import io.rotlabs.famcardcontainer.data.model.Gradient
import io.rotlabs.famcardcontainer.utils.UrlOpener
import io.rotlabs.famcardcontainer.utils.text.TextUtils

object CardDisplayUtils {

    fun setBackgroundGradient(view: View, gradient: Gradient?, cornerRadius: Float = 0.0f) {
        gradient?.let {
            val colorIntArray = gradient.colors.map {
                Color.parseColor(it)
            }.toIntArray()
            val gradientDrawable =
                GradientDrawable(GradientDrawable.Orientation.BL_TR, colorIntArray)

            gradientDrawable.setCornerRadius(cornerRadius)


            view.background = gradientDrawable
        }
    }

    fun setBackgroundColor(view: View, colorHex: String?) {
        colorHex?.let { hex ->
            val color = Color.parseColor(hex)
            if (view is CardView) {
                view.setCardBackgroundColor(color)
            } else {
                view.setBackgroundColor(color)
            }
        }
    }

    fun setBackgroundImage(
        view: View,
        cardImage: CardImage?,
        roundCornerRadius: Int = 0,
        margin: Int = 0,
        isHC9: Boolean = false,
        hc9height: Int? = null,
        spanCount: Int = 1
    ) {
        cardImage?.let { bgImage ->
            val requestOptions = RequestOptions().apply {
                diskCacheStrategy(DiskCacheStrategy.ALL)
                transform(RoundedCorners(roundCornerRadius))

                if (!isHC9) {
                    val size = getSize(bgImage.aspectRatio, margin, spanCount)
                    override(size.first, size.second)
                } else {
                    hc9height?.let { height ->
                        val size = getSizeHC9(bgImage.aspectRatio, height)
                        override(size.first, size.second)
                    }
                }
            }

            Glide.with(view)
                .asBitmap()
                .apply(requestOptions)
                .load(bgImage.imageUrl)
                .addListener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        e?.printStackTrace()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        resource?.let { bitmap ->
                            view.background = BitmapDrawable(view.resources, bitmap)
                        }
                        return true
                    }

                })
                .submit()
        }
    }

    fun setTextToView(view: TextView, formattedText: FormattedText?, fallbackText: String) {
        if (formattedText != null) {
            try {
                val spannableText = TextUtils.formatToSpannableString(view.context, formattedText)
                view.text = spannableText
            } catch (e: Exception) {
                e.printStackTrace()
                view.text = fallbackText
            }
        } else {
            view.text = fallbackText
        }
    }


    fun setIcon(view: ImageView, cardImage: CardImage?) {
        if (cardImage != null) {
            if (cardImage.imageType == "external" || cardImage.imageType == "ext") {
                val requestOptions = RequestOptions().apply {
                    diskCacheStrategy(DiskCacheStrategy.ALL)
                    placeholder(R.drawable.ic_small_display_icon_place_holder)
                    error(R.drawable.ic_small_display_icon_place_holder)
                    circleCrop()
                }


                Glide.with(view)
                    .asBitmap()
                    .load(cardImage.imageUrl)
                    .apply(requestOptions)
                    .into(view)

            } else {
                view.setImageResource(R.drawable.ic_small_display_icon_place_holder)
            }
        } else {
            view.setImageResource(R.drawable.ic_small_display_icon_place_holder)
        }
    }

    fun setUrlAction(view: View, url: String?) {
        url?.let { urlString ->
            view.setOnClickListener {
                UrlOpener.openUrl(urlString, it.context)
            }
        }
    }


    fun setViewToAspectRatio(view: View, aspectRatio: Double, margin: Int = 0, spanCount: Int = 1) {
        val params = view.layoutParams
        params.width = (ScreenUtils.getScreenWidth() - (4 * margin)) / spanCount
        params.height = (params.width / aspectRatio.toSafeAspectRatio()).toInt()

        view.layoutParams = params
    }

    fun setViewToAspectRatioHC9(view: View, aspectRatio: Double, height: Int) {
        val params = view.layoutParams
        params.height = height.dp
        val width = (height.dp * aspectRatio.toSafeAspectRatio()).toInt()
        params.width = width
        view.layoutParams = params
    }

    private fun getSize(aspectRatio: Double, margin: Int, spanCount: Int): Pair<Int, Int> {
        val width = ScreenUtils.getScreenWidth() - (4 * margin)
        val height = (width / aspectRatio.toSafeAspectRatio()).toInt()
        return width / spanCount to height
    }

    private fun getSizeHC9(aspectRatio: Double, height: Int): Pair<Int, Int> {
        val width = (height.dp * aspectRatio.toSafeAspectRatio()).toInt()
        return width to height
    }

    private fun Double.toSafeAspectRatio(): Double {
        return if (this <= 0.0) 1.0 else this
    }


    fun resizeView(view: View, scaleFactor: Float) {
        val params = view.layoutParams
        if (params is ViewGroup.MarginLayoutParams) {
            params.marginStart = (params.marginStart * scaleFactor).toInt()
            params.marginEnd = (params.marginEnd * scaleFactor).toInt()
            params.bottomMargin = (params.topMargin * scaleFactor).toInt()
            params.topMargin = (params.bottomMargin * scaleFactor).toInt()
        }

        if (params != null) {
            view.layoutParams = params
        }

        view.setPadding(
            (view.paddingStart * scaleFactor).toInt(),
            (view.paddingEnd * scaleFactor).toInt(),
            (view.paddingTop * scaleFactor).toInt(),
            (view.paddingBottom * scaleFactor).toInt(),
        )
        view.scaleX = scaleFactor
        view.scaleY = scaleFactor
    }

}