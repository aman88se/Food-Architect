package com.semsols.foodarchitect.data.remote

import com.semsols.foodarchitect.data.model.MealDTO
import com.semsols.foodarchitect.data.model.MealDataTransferObject
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchAPI {

    @GET("api/json/v1/1/search.php")
    suspend fun getFoodItemList(@Query("s")s: String): MealDataTransferObject

    @GET("api/json/v1/1/lookup.php")
    suspend fun getFoodDetails(@Query("i")i: String): MealDataTransferObject
}