package com.semsols.foodarchitect.domain.feature

import com.semsols.foodarchitect.common.Resource
import com.semsols.foodarchitect.data.model.toDomainLayerMeal
import com.semsols.foodarchitect.domain.model.Meal
import com.semsols.foodarchitect.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.Flow
import javax.inject.Inject

class GetMealSearchList @Inject constructor(private val repository: MealSearchRepository) {

    operator fun invoke(s: String): kotlinx.coroutines.flow.Flow<Resource<List<Meal>>> = flow {
        try{
            emit(Resource.Loading())
            val response = repository.getFoodList(s)
            val list = if(response.meals.isNullOrEmpty()){
                emptyList<Meal>()
            }
            else{
                response.meals.map { it.toDomainLayerMeal() }
            }

            emit(Resource.Success(data = list))


        } catch (e: HttpException){

            emit(Resource.Error(message = e.localizedMessage?: "Unknown Error"))

        } catch (e: IOException){

            emit(Resource.Error(message = e.localizedMessage?: "No Internet"))

        } catch (e: Exception){
        }
    }

}