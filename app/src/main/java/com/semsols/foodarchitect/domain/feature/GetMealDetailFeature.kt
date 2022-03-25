package com.semsols.foodarchitect.domain.feature

import com.semsols.foodarchitect.common.Resource
import com.semsols.foodarchitect.data.model.toDomainLayerMealDetails
import com.semsols.foodarchitect.domain.model.Meal
import com.semsols.foodarchitect.domain.model.MealDetails
import com.semsols.foodarchitect.domain.repository.MealDetailRepository
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

import java.util.concurrent.Flow
import javax.inject.Inject

class GetMealDetailFeature @Inject constructor(private val repository: MealDetailRepository) {

    operator fun invoke(id: String): kotlinx.coroutines.flow.Flow<Resource<MealDetails>> = flow{
        try {

            emit(Resource.Loading())
            val response = repository.getMealDetails(id).meals[0].toDomainLayerMealDetails()
            emit(Resource.Success(data = response))


        }catch(e: HttpException){

        }catch(e: IOException){

        }catch(e: Exception){

        }
    }
}