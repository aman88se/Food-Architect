package com.semsols.foodarchitect.ui.FoodSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semsols.foodarchitect.R
import com.semsols.foodarchitect.databinding.ViewHolderSearchListBinding
import com.semsols.foodarchitect.domain.model.Meal

class FoodSearchAdapter : RecyclerView.Adapter<FoodSearchAdapter.MyViewHolder>(){

    private var listener :((Meal)->Unit)?=null

    var list = mutableListOf<Meal>()

    fun setContentList(list: MutableList<Meal>){

        this.list = list
        notifyDataSetChanged()

    }


    class MyViewHolder(val viewHolder: ViewHolderSearchListBinding) :
    RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodSearchAdapter.MyViewHolder {
        val binding = ViewHolderSearchListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MyViewHolder(binding)


    }

    fun itemClickListener(l:(Meal) -> Unit){
        listener = l
    }





    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.meal = this.list[position]

        holder.viewHolder.root.setOnClickListener {

            listener?.let {
                it(this.list[position])
            }

        }




    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}

