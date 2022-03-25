package com.semsols.foodarchitect.ui.FoodSearch

import com.semsols.foodarchitect.domain.model.Meal

data class FoodSearchState(

    val data: List<Meal>? = null,
    val error: String = "",
    val loading: Boolean = false

) {
}