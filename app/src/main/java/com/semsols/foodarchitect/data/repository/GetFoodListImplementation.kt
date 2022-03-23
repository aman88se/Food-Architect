package com.semsols.foodarchitect.data.repository

import com.semsols.foodarchitect.data.model.MealDataTransferObject
import com.semsols.foodarchitect.data.remote.MealSearchAPI
import com.semsols.foodarchitect.domain.repository.MealSearchRepository

class GetFoodListImplementation(

    private val mealSearchAPI: MealSearchAPI

): MealSearchRepository {

    override suspend fun getFoodList(s: String): MealDataTransferObject {
        return mealSearchAPI.getFoodItemList(s)
    }
}