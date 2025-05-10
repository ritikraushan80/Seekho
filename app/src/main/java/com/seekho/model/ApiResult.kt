package com.seekho.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ritik on: 10/05/25
 */

@Keep
data class ApiResult<T>(

    @SerializedName("data") @Expose var data: T,

    @SerializedName("pagination") @Expose var pagination: Pagination,

    )