package com.semsols.foodarchitect.ui.FoodDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semsols.foodarchitect.common.Resource

import com.semsols.foodarchitect.domain.feature.GetMealDetailFeature
import com.semsols.foodarchitect.domain.feature.GetMealSearchListFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class FoodDetailsViewModel @Inject constructor(private val getMealDetailListFeature: GetMealDetailFeature):
  ViewModel() {


      private val FoodDetails = MutableStateFlow<FoodDetailState>(FoodDetailState())
      val foodDetails: StateFlow<FoodDetailState> = FoodDetails



    fun getFoodDetails(id: String){
        getMealDetailListFeature(id).onEach {

            when(it){

                is Resource.Loading ->{
                    FoodDetails.value =  FoodDetailState(loading = true)
                }
                is Resource.Error ->{
                    FoodDetails.value = FoodDetailState(error = it.message?:"")
                }
                is Resource.Success ->{
                    FoodDetails.value = FoodDetailState(data = it.data)
                }

            }
        }.launchIn(viewModelScope)

    }






}
