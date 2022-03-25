package com.semsols.foodarchitect.ui.FoodDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.semsols.foodarchitect.databinding.FragmentFoodDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class FoodDetailsFragment : Fragment() {


    private var Binding: FragmentFoodDetailsBinding? = null
    val binding: FragmentFoodDetailsBinding
    get() = Binding!!


    private val viewModelDetail: FoodDetailsViewModel by viewModels()

    private val args: FoodDetailsFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)
        return Binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        args.mealId?.let {

            viewModelDetail.getFoodDetails(it)

        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModelDetail.foodDetails.collect {

                if (it.loading){


                }
                if(it.error.isNotBlank()){

                    Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()

                }

                it.data.let {
                    binding.mealDetails = it
                }


            }
        }

        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }




    }



}