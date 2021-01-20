package io.rotlabs.famcardcontainer.ui

import io.reactivex.disposables.CompositeDisposable
import io.rotlabs.famcardcontainer.data.remote.RestClient
import io.rotlabs.famcardcontainer.utils.OnErrorResponse
import io.rotlabs.famcardcontainer.utils.OnSuccessResponse
import io.rotlabs.famcardcontainer.utils.rx.SchedulerProvider

class ContainerViewModel(
    private val onSuccessResponse: OnSuccessResponse,
    private val onErrorResponse: OnErrorResponse,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider
) {

    fun loadCardGroupResponse(url: String) {
        compositeDisposable.add(
            RestClient.getCards(url)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    {
                        onSuccessResponse.onSuccess(it)
                    }, {
                        onErrorResponse.onError(it.message)
                    }
                )
        )
    }

}