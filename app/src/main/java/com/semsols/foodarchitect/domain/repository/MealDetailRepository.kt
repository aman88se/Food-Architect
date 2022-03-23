package com.semsols.foodarchitect.domain.repository

import com.semsols.foodarchitect.data.model.MealDataTransferObject

interface MealDetailRepository {

    suspend fun getMealDetails(id: String): MealDataTransferObject
}