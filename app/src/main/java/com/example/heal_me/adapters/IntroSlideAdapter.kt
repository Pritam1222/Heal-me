package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.heal_me.R
import com.example.heal_me.data.IntroSlide

class IntroSlideAdapter(private val introSlides: List<IntroSlide>) :
     RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.intro_slider_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    inner class IntroSlideViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val textTitle = view.findViewById<TextView>(R.id.txt1)
        private val textDescription = view.findViewById<TextView>(R.id.txt2)
        private val imageIcon = view.findViewById<ImageView>(R.id.introImage)

        fun bind(introSlide: IntroSlide){
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)

        }
    }

}