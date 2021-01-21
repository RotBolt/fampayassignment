package io.rotlabs.famcardcontainer.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.rotlabs.famcardcontainer.R
import io.rotlabs.famcardcontainer.data.local.RemovedCardsDetailsHolder
import io.rotlabs.famcardcontainer.data.model.CardGroup
import io.rotlabs.famcardcontainer.data.remote.response.CardGroupResponse
import io.rotlabs.famcardcontainer.ui.cardgroups.CardGroupAdapter
import io.rotlabs.famcardcontainer.utils.OnErrorResponse
import io.rotlabs.famcardcontainer.utils.OnSuccessResponse
import io.rotlabs.famcardcontainer.utils.getAppSharedPrefs
import io.rotlabs.famcardcontainer.utils.rx.RxSchedulerProvider
import kotlinx.android.synthetic.main.layout_card_groups.view.*

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

    private lateinit var cardGroupAdapter: CardGroupAdapter

    private fun init() {
        compositeDisposable = CompositeDisposable()

        containerViewModel = ContainerViewModel(
            this,
            this,
            compositeDisposable,
            RxSchedulerProvider()
        )

        val removedCardsDetailsHolder = RemovedCardsDetailsHolder(context.getAppSharedPrefs())
        cardGroupAdapter = CardGroupAdapter(arrayListOf(), removedCardsDetailsHolder)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        setupCardGroupRecyclerView(cardGroupAdapter, layoutManager)


    }

    private fun setupCardGroupRecyclerView(
        cardGroupAdapter: CardGroupAdapter,
        layoutManager: LinearLayoutManager
    ) {

        val rvCardGroupLayout =
            LayoutInflater.from(context).inflate(R.layout.layout_card_groups, this, false)
        addView(rvCardGroupLayout)
        rvCardGroups.layoutManager = layoutManager
        rvCardGroups.adapter = cardGroupAdapter
    }

    fun load(url: String) {
        containerViewModel.loadCardGroupResponse(url)
    }

    override fun onSuccess(cardGroupResponse: CardGroupResponse) {
        // pass data to adapter
        Log.d("PUI", "Response size ${cardGroupResponse.cardGroups.size}")
        val cardGroupList = arrayListOf<CardGroup>()
        cardGroupList.addAll(cardGroupResponse.cardGroups)
        cardGroupAdapter.updateAllItems(cardGroupList)

    }

    override fun onError(errorMessage: String?) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDetachedFromWindow() {
        compositeDisposable.clear()
        super.onDetachedFromWindow()
    }
}