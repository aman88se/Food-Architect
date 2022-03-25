package com.semsols.foodarchitect.ui.FoodSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semsols.foodarchitect.common.Resource
import com.semsols.foodarchitect.domain.feature.GetMealSearchListFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FoodSearchViewModel @Inject constructor(private val getMealSearchListFeature: GetMealSearchListFeature) :
    ViewModel() {


        val FoodSearchList = MutableStateFlow<FoodSearchState>(FoodSearchState())
        val foodSearchList: StateFlow<FoodSearchState> = FoodSearchList



    fun searchFoodList(s: String){

        getMealSearchListFeature(s).onEach {

            when(it){

                is Resource.Loading -> {

                    FoodSearchList.value = FoodSearchState(loading = true)

                }
                is Resource.Error -> {

                    FoodSearchList.value = FoodSearchState(error = it.message?:"")

                }
                is Resource.Success -> {

                    FoodSearchList.value = FoodSearchState(data = it.data)

                }

            }

        }.launchIn(viewModelScope)

    }





}