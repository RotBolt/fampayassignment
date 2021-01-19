package io.rotlabs.famcardcontainer.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.reactivex.disposables.CompositeDisposable
import io.rotlabs.famcardcontainer.data.remote.response.CardGroupResponse
import io.rotlabs.famcardcontainer.utils.RxSchedulerProvider
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

class FamCardContainer : FrameLayout, OnSuccessResponse, OnErrorResponse {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private lateinit var containerViewModel: ContainerViewModel
    private lateinit var compositeDisposable: CompositeDisposable

    private fun init() {
        compositeDisposable = CompositeDisposable()
        containerViewModel = ContainerViewModel(
            this,
            this,
            compositeDisposable,
            RxSchedulerProvider()
        )
    }

    fun load(url: String) {
        containerViewModel.loadCardGroupResponse(url)
    }

    override fun onSuccess(cardGroupResponse: CardGroupResponse) {
        // pass data to adapter
        Log.d("PUI", "Response size ${cardGroupResponse.cardGroups.size}")
    }

    override fun onError(errorMessage: String?) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDetachedFromWindow() {
        compositeDisposable.clear()
        super.onDetachedFromWindow()
    }
}