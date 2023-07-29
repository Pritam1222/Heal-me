package com.example.heal_me.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.heal_me.R
import com.example.heal_me.fragments.HomeFragment
import com.example.heal_me.model.SymptomItems

class AllSymptomsAdapters(var context: Context, var arrayList: ArrayList<SymptomItems>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View = View.inflate(context, R.layout.symptoms_all, null)
        var images: ImageView = view.findViewById(R.id.symptom_image)
        var names: TextView = view.findViewById(R.id.symptom_name)

        var listItems:SymptomItems = arrayList.get(position)

        images.setImageResource(listItems.images!!)
        names.text = listItems.name

        return view

    }
}