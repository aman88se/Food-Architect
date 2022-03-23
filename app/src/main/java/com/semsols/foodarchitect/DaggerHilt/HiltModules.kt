package com.semsols.foodarchitect.DaggerHilt

import com.semsols.foodarchitect.common.Constant
import com.semsols.foodarchitect.data.remote.MealSearchAPI
import com.semsols.foodarchitect.data.repository.GetFoodListImplementation
import com.semsols.foodarchitect.data.repository.GetMealDetailImplementation
import com.semsols.foodarchitect.domain.repository.MealDetailRepository
import com.semsols.foodarchitect.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object HiltModules {

    @Provides
    @Singleton

    fun provideFoodAPI(): MealSearchAPI{

        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchAPI::class.java)
    }

    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository{

        return GetFoodListImplementation(mealSearchAPI)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchAPI: MealSearchAPI): MealDetailRepository{

        return GetMealDetailImplementation(mealSearchAPI)
    }
}