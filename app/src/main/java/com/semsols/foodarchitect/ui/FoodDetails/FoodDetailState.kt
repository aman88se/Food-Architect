package com.semsols.foodarchitect.ui.FoodDetails

import com.semsols.foodarchitect.domain.model.Meal
import com.semsols.foodarchitect.domain.model.MealDetails

data class FoodDetailState(

    val data: MealDetails? = null,
    val error: String = "",
    val loading: Boolean = false

) {
}