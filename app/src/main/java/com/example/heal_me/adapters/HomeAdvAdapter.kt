package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.heal_me.R
import com.example.heal_me.data.HomeAdvData

class HomeAdvAdapter(private val advSlides: List<HomeAdvData>) :
    RecyclerView.Adapter<HomeAdvAdapter.HomeAdvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdvViewHolder {
        return HomeAdvViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_advertise,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeAdvViewHolder, position: Int) {
        holder.bind(advSlides[position])
    }

    override fun getItemCount(): Int {
        return advSlides.size
    }

    inner class HomeAdvViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val text1 = view.findViewById<TextView>(R.id.ad_text_1)
        private val text2 = view.findViewById<TextView>(R.id.ad_text_2)
        private val image = view.findViewById<ImageView>(R.id.ad_image)

        fun bind(advSlides: HomeAdvData){
            text1.text = advSlides.text_1
            text2.text = advSlides.text_2
            image.setImageResource(advSlides.image)

        }
    }

}