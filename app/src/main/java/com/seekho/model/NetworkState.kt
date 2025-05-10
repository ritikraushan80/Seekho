package com.seekho.model

import androidx.annotation.Keep
import androidx.annotation.StringRes
import com.seekho.helpers.StringConstants.CONNECTION_ERROR

/**
 * Created by Ritik on: 10/05/25
 */

const val DEFAULT_ERROR_CODE = -1

@Keep
sealed class NetworkState<out T> {
    class Loading<out T> : NetworkState<T>()

    @Keep
    data class Success<out T>(
        @Keep
        val data: T?,
        val pagination: Pagination? = null
    ) : NetworkState<T>()

    data class Error(
        val message: String? = null,
        val errCode: Int = DEFAULT_ERROR_CODE,
        val t: Throwable = Throwable(CONNECTION_ERROR),
        @StringRes val stringRes: Int? = null,
        val errorModel: ErrorModel? = null
    ) : NetworkState<Nothing>()

    data class Failure<out T>(val throwable: String?) : NetworkState<T>()
}

fun <T> NetworkState<T>.data(): T? {
    return when (this) {
        is NetworkState.Success -> this.data
        else -> null
    }
}
