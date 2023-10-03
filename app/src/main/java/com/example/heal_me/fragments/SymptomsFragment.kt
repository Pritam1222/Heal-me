package com.example.heal_me.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.adapters.AllSymptomsAdapters
import com.example.heal_me.databinding.FragmentSymptomsBinding
import com.example.heal_me.model.SymptomItems
import com.google.android.material.bottomnavigation.BottomNavigationView

class SymptomsFragment : Fragment() {

    private var _binding: FragmentSymptomsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController : NavController

    private var arrayList:ArrayList<SymptomItems> ? = null
    private var gridView: GridView? = null
    private var allSymptomsAdapter: AllSymptomsAdapters? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSymptomsBinding.inflate(inflater, container, false)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = binding.toolbarSymptoms
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val navHostFragment = (context as FragmentActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        gridView = binding.symptomGridView
        arrayList = setDataList()
        allSymptomsAdapter = AllSymptomsAdapters(requireContext(), arrayList!!)
        gridView?.adapter = allSymptomsAdapter
        // Set an item click listener for the GridView
        gridView!!.setOnItemClickListener { _, _, position, _ ->
            // Handle item click by opening a new fragment
            val action =
                SymptomsFragmentDirections.actionSymptomsFragmentToDoctorsFragment()
            navController.navigate(action)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setDataList() : ArrayList<SymptomItems>{
        var arrayList:ArrayList<SymptomItems> = ArrayList()

        arrayList.add(SymptomItems(R.drawable.intestine,"Digestion Problem"))
        arrayList.add(SymptomItems(R.drawable.ear_ache,"Ear Pain"))
        arrayList.add(SymptomItems(R.drawable.esophagus,"Throat Problem"))
        arrayList.add(SymptomItems(R.drawable.lungs,"Lungs Problem"))
        arrayList.add(SymptomItems(R.drawable.milk_tooth, "Dental Problem"))
        arrayList.add(SymptomItems(R.drawable.heart_surgery, "Heart Problem"))
        arrayList.add(SymptomItems(R.drawable.brain, "Mental Wellness"))
        arrayList.add(SymptomItems(R.drawable.joint_care, "Bone & Joints"))
        arrayList.add(SymptomItems(R.drawable.cold,"Cold"))
        arrayList.add(SymptomItems(R.drawable.anxiety,"Headache"))
        arrayList.add(SymptomItems(R.drawable.eye_care, "Eye Problem"))
        arrayList.add(SymptomItems(R.drawable.stomach_ache, "Kidney & urinary tract"))


//        arrayList.add(SymptomItems(R.drawable.women_health, "Women's Health"))
//        arrayList.add(SymptomItems(R.drawable.skincare, "Skin & Hair"))
//        arrayList.add(SymptomItems(R.drawable.baby_dress, "Child Specialist"))
//        arrayList.add(SymptomItems(R.drawable.general_physician, "General Physician"))
//        arrayList.add(SymptomItems(R.drawable.milk_tooth, "Dental Care"))
//        arrayList.add(SymptomItems(R.drawable.esophagus, "Ear Nose Throat"))
//        arrayList.add(SymptomItems(R.drawable.joint_care, "Bone & Joints"))
//        arrayList.add(SymptomItems(R.drawable.brain, "Mental Wellness"))
//        arrayList.add(SymptomItems(R.drawable.heart_surgery, "Heart Surgery"))
//        arrayList.add(SymptomItems(R.drawable.lungs, "lungs"))
//        arrayList.add(SymptomItems(R.drawable.intestine, "Digestion"))
//        arrayList.add(SymptomItems(R.drawable.stomach_ache, "Kidney & urinary tract"))
//        arrayList.add(SymptomItems(R.drawable.dietitian, "Dietitian"))
//        arrayList.add(SymptomItems(R.drawable.eye_care, "Eye Care"))

        return arrayList
    }

}