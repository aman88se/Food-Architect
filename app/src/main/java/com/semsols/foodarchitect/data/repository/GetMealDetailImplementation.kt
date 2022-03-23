package com.semsols.foodarchitect.data.repository

import com.semsols.foodarchitect.data.model.MealDataTransferObject
import com.semsols.foodarchitect.data.remote.MealSearchAPI
import com.semsols.foodarchitect.domain.repository.MealDetailRepository
import com.semsols.foodarchitect.domain.repository.MealSearchRepository

class GetMealDetailImplementation(

    private val mealSearchAPI: MealSearchAPI

): MealDetailRepository {


    override suspend fun getMealDetails(id: String): MealDataTransferObject {

        return mealSearchAPI.getFoodDetails(id)

    }


}