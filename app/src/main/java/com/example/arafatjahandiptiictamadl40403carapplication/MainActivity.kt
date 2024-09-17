package com.example.arafatjahandiptiictamadl40403carapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arafatjahandiptiictamadl40403carapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var carAdapter: CarAdapter
    val car = ArrayList<Car>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.carRv.layoutManager = LinearLayoutManager(this)



        car.add(Car("BMW", 20, 4.0, R.drawable.bmww, "Car"))
        car.add(Car("Audi", 25, 5.0, R.drawable.bmww, "Luxury car"))
        car.add(Car("Mercedes", 30, 6.0, R.drawable.bmww, "Luxury sedan"))
        car.add(Car("Toyota", 15, 3.5, R.drawable.bmww, "Reliable vehicle"))
        car.add(Car("Ford", 20, 4.5, R.drawable.bmww, "American muscle"))
        car.add(Car("Honda", 18, 3.8, R.drawable.bmww, "Fuel efficient"))
        car.add(Car("Chevrolet", 22, 4.2, R.drawable.bmww, "American classic"))
        car.add(Car("Nissan", 19, 4.0, R.drawable.bmww, "Versatile vehicle"))
        car.add(Car("Hyundai", 16, 3.2, R.drawable.bmww, "Affordable car"))
        car.add(Car("Kia", 17, 3.4, R.drawable.bmww, "Compact car"))
        car.add(Car("Subaru", 21, 4.3, R.drawable.bmww, "All-wheel drive"))
        car.add(Car("Volkswagen", 23, 4.6, R.drawable.bmww, "European car"))
        car.add(Car("Mazda", 20, 4.1, R.drawable.bmww, "Sporty car"))
        car.add(Car("Land Rover", 35, 6.5, R.drawable.bmww, "Off-road vehicle"))
        car.add(Car("Jaguar", 40, 7.0, R.drawable.bmww, "Luxury sports car"))
        car.add(Car("Porsche", 45, 7.5, R.drawable.bmww, "High performance"))
        car.add(Car("Ferrari", 50, 8.0, R.drawable.bmww, "Exotic sports car"))
        car.add(Car("Lamborghini", 55, 8.5, R.drawable.bmww, "Supercar"))
        car.add(Car("Maserati", 48, 7.8, R.drawable.bmww, "Italian luxury"))
        car.add(Car("Alfa Romeo", 28, 5.5, R.drawable.bmww, "Stylish car"))
        car.add(Car("Bentley", 60, 9.0, R.drawable.bmww, "Luxury and comfort"))
        car.add(Car("Rolls Royce", 70, 10.0, R.drawable.bmww, "Ultimate luxury"))
        car.add(Car("Aston Martin", 65, 9.5, R.drawable.bmww, "Elegant sports car"))
        car.add(Car("Bugatti", 80, 10.5, R.drawable.bmww, "High-speed supercar"))
        car.add(Car("Pagani", 75, 10.0, R.drawable.bmww, "Exclusive sports car"))
        car.add(Car("Koenigsegg", 85, 11.0, R.drawable.bmww, "Hypercar"))
        car.add(Car("Lotus", 25, 5.5, R.drawable.bmww, "Lightweight sports car"))
        car.add(Car("McLaren", 60, 9.0, R.drawable.bmww, "Performance car"))
        car.add(Car("Genesis", 30, 6.0, R.drawable.bmww, "Luxury sedan"))
        car.add(Car("Infiniti", 28, 5.8, R.drawable.bmww, "Luxury and performance"))
        car.add(Car("Lincoln", 32, 6.2, R.drawable.bmww, "Luxury vehicle"))
        car.add(Car("Chrysler", 22, 4.5, R.drawable.bmww, "American car"))





        carAdapter = CarAdapter(car)
        binding.carRv.adapter = carAdapter

        carAdapter.onClick={
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", it.carName)
            intent.putExtra("price", it.carPrice)
            intent.putExtra("quantity", it.carModel)
            intent.putExtra("desc", it.carDesc)
            intent.putExtra("image", it.carImg)
            startActivity(intent)
        }

        binding.addBtn.setOnClickListener {
            showCarAddDialog()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                car.removeAt(viewHolder.adapterPosition)
                carAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.carRv)



    }

    private fun showCarAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_car_layout, null)
        val nameEt = dialogView.findViewById<EditText>(R.id.carNameEt)
        val priceEt = dialogView.findViewById<EditText>(R.id.carPriceEt)
        val modelEt = dialogView.findViewById<EditText>(R.id.carModelEt)
        val descEt = dialogView.findViewById<EditText>(R.id.carDescEt)

        AlertDialog.Builder(this)
            .setTitle("Add Car")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEt.text.toString()
                val price = priceEt.text.toString().toDouble()
                val model = modelEt.text.toString().toInt()
                val desc = descEt.text.toString()
                val img = R.drawable.bmww
                car.add(Car(name, model, price, img, desc))
                carAdapter.notifyItemInserted(car.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()

    }


}



