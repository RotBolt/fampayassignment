package io.rotlabs.famcardcontainer.ui.cardgroups.cards.smallDisplayCard

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.model.Card
import io.rotlabs.famcardcontainer.data.model.CardImage
import io.rotlabs.famcardcontainer.data.model.FormattedText
import io.rotlabs.famcardcontainer.ui.base.BaseViewHolder
import io.rotlabs.famcardcontainer.utils.UrlOpener
import io.rotlabs.famcardcontainer.utils.display.CardDisplayUtils
import io.rotlabs.famcardcontainer.utils.text.TextUtils
import kotlinx.android.synthetic.main.item_small_display_card.view.*

class SmallDisplayCardViewHolder(parent: ViewGroup) :
    BaseViewHolder<Card>(parent, R.layout.item_small_display_card) {

    companion object {
        const val TAG = "SmallDisplayCardHolder"
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

        CardDisplayUtils.setBackgroundColor(itemView, data.bgColor)
        CardDisplayUtils.setBackgroundGradient(itemView, data.bgGradient)
        CardDisplayUtils.setBackgroundImage(itemView, data.bgImage,8)
    }


}