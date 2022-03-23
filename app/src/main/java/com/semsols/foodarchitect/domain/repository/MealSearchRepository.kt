package com.semsols.foodarchitect.domain.repository

import com.semsols.foodarchitect.data.model.MealDataTransferObject

interface MealSearchRepository {

    suspend fun getFoodList(s: String): MealDataTransferObject
}