package io.rotlabs.famcardcontainer.utils.text

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import io.rotlabs.famcardcontainer.data.model.Entity
import io.rotlabs.famcardcontainer.data.model.FormattedText
import io.rotlabs.famcardcontainer.utils.UrlOpener

object TextUtils {

    fun formatToSpannableString(context: Context, formattedText: FormattedText): SpannableString {
        val textToParse = formattedText.text

        var ssb = SpannableStringBuilder(textToParse)
        formattedText.entities.forEach {

            val i = ssb.indexOf("{}")
            ssb = ssb.replace(i, i + 1, it.text)

            setColor(ssb, i, i + it.text.length - 1, it)
            setUrlSpan(ssb, i, i + it.text.length - 1, it, context)
            setFontStyleSpan(ssb, i, i + it.text.length - 1, it)
        }

        return SpannableString(ssb)
    }


    private fun setColor(ssb: SpannableStringBuilder, start: Int, end: Int, entity: Entity) {
        entity.color?.let { hex ->
            val color = Color.parseColor(hex)
            val foregroundColorSpan = ForegroundColorSpan(color)
            ssb.setSpan(
                foregroundColorSpan,
                start, end,
                SpannableString.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
    }

    private fun setUrlSpan(
        ssb: SpannableStringBuilder,
        start: Int,
        end: Int,
        entity: Entity,
        context: Context
    ) {
        entity.url?.let { url ->
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    UrlOpener.openUrl(url, context)
                }

            }
            ssb.setSpan(
                clickableSpan,
                start,
                end,
                SpannableString.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
    }

    private fun setFontStyleSpan(
        ssb: SpannableStringBuilder,
        start: Int,
        end: Int,
        entity: Entity
    ) {
        entity.fontStyle?.let { fontStyle ->
            if (fontStyle == "italic") {
                val styleSpan = StyleSpan(Typeface.ITALIC)
                ssb.setSpan(
                    styleSpan,
                    start,
                    end,
                    SpannableString.SPAN_INCLUSIVE_INCLUSIVE
                )
            } else if (fontStyle == "underline") {
                val underlineSpan = UnderlineSpan()
                ssb.setSpan(
                    underlineSpan,
                    start,
                    end,
                    SpannableString.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
        }
    }

}