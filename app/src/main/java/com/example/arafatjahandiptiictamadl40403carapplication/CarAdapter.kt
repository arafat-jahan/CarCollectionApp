package com.example.arafatjahandiptiictamadl40403carapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.arafatjahandiptiictamadl40403carapplication.databinding.CarItemBinding

class CarAdapter(private val carList: ArrayList<Car>) : RecyclerView.Adapter<CarAdapter.MyViewHolder>() {

    var onClick: ((Car) -> Unit)? = null

    class MyViewHolder(val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val car = carList[position]

        holder.binding.apply {
            carName.text = car.carName
            carModel.text = "Model: " + car.carModel
            carPrice.text = "Price: $" + car.carPrice.toString()
            carImg.setImageResource(car.carImg)
        }

        holder.itemView.setOnClickListener {
            onClick?.invoke(car)
        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Car Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes") { _, _ ->
                    carList.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }
    }
}
