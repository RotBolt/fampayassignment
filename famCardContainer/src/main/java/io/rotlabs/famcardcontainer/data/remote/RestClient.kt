package io.rotlabs.famcardcontainer.data.remote

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.rotlabs.famcardcontainer.data.remote.response.CardGroupResponse
import okhttp3.*
import java.io.IOException
import java.lang.IllegalStateException
import java.lang.reflect.Type

object RestClient {

    private val httpClient = OkHttpClient()

    fun getCards(url: String): Single<CardGroupResponse> {
        return Single.create {
            handleRequest(url, it, CardGroupResponse::class.java)
        }
    }

    private fun <T> handleRequest(url: String, emitter: SingleEmitter<T>, clazz: Class<T>) {
        try {
            val request = Request.Builder().url(url).build()
            httpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    emitter.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseBodyJsonString = response.body?.string()
                    val gson = Gson()
                    try {
                        val data: T =
                            gson.fromJson(responseBodyJsonString, clazz)
                        emitter.onSuccess(data!!)
                    } catch (jsonSyntaxException: JsonSyntaxException) {
                        emitter.onError(jsonSyntaxException)
                    } catch (e: Exception) {
                        emitter.onError(e)
                    }
                }
            })
        } catch (illegal: IllegalStateException) {
            emitter.onError(illegal)
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}