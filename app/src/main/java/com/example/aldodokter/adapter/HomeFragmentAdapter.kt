package com.example.aldodokter.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aldodokter.R
import com.example.aldodokter.controller.activity.MyCustomAppIntro
import com.example.aldodokter.entity.DataImage

class HomeFragmentAdapter : RecyclerView.Adapter<HomeFragmentAdapter.AnimalHolder>() {

    private val tag : String = HomeFragmentAdapter::class.java.simpleName
    private val animal = listOf(
        DataImage("Turtle",R.drawable.kura_kura2),
        DataImage("Sea Star",R.drawable.seastar1),
        DataImage("Plankton",R.drawable.plankton1)

    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)
        return AnimalHolder(v)
    }

    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {
        var name = holder.name
        name.text = animal[position].name
//        holder.image.setImageResource(animal[position].image!!)

        if (name.text.equals("Turtle")){
            holder.image.setImageResource(R.drawable.kura_kura2)
        } else if (name.text.equals("Sea Star")){
            holder.image.setImageResource(R.drawable.seastar1)
        } else if (name.text.equals("Plankton")){
            holder.image.setImageResource(R.drawable.plankton1)
        }
    }

    override fun getItemCount(): Int {
        return animal.size
    }


    inner class AnimalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView
        var image: ImageView


        init {
            name = itemView.findViewById(R.id.tv_animal)
            image = itemView.findViewById(R.id.image_animal)

            itemView.setOnClickListener {
                var position: Int = getAdapterPosition()
                val context = itemView.context
                val intent = Intent(context, MyCustomAppIntro::class.java).apply {
                    putExtra("number", position)
                    putExtra("name", name.text)
                    Log.d(tag,"check value extra : \n position selected : $position \n name selected : ${name.text}")
                }
                context.startActivity(intent)
            }
        }
    }
}


