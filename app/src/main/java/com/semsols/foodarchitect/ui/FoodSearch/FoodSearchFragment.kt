package com.semsols.foodarchitect.ui.FoodSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.semsols.foodarchitect.R
import com.semsols.foodarchitect.databinding.FragmentFoodSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FoodSearchFragment : Fragment() {

    private var Binding: FragmentFoodSearchBinding? = null
    val binding: FragmentFoodSearchBinding
    get()  = Binding!!

    private val foodSearchViewModel: FoodSearchViewModel by viewModels()

    private val foodSearchAdapter = FoodSearchAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentFoodSearchBinding.inflate(inflater, container,false)
        return Binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.searchFood.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {

                    foodSearchViewModel.searchFoodList(it)

                }
                return false


            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })

        binding.searchRecyclerView.apply {
            adapter = foodSearchAdapter
        }


        lifecycle.coroutineScope.launchWhenCreated {
            foodSearchViewModel.foodSearchList.collect {

                if(it.loading){

                    binding.progressMealSearch.visibility = View.VISIBLE

                }
                if(it.error.isNotBlank()){

                    binding.progressMealSearch.visibility = View.GONE
                }

                it.data?.let {
                    binding.progressMealSearch.visibility = View.GONE
                    foodSearchAdapter.setContentList(it.toMutableList())
                }

            }
        }


        foodSearchAdapter.itemClickListener {
            findNavController().navigate(
                FoodSearchFragmentDirections.actionFoodSearchFragmentToFoodDetailsFragment(
                    mealId = it.mealId
                )
            )

        }



    }


}