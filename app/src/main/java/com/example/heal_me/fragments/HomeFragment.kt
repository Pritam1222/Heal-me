package com.example.heal_me.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.heal_me.R
import com.example.heal_me.adapters.DoctorsInfoAdapter
import com.example.heal_me.adapters.HomeAdvAdapter
import com.example.heal_me.adapters.SymptomsAdapters
import com.example.heal_me.data.DoctorsInfoDataItem
import com.example.heal_me.data.HomeAdvData
import com.example.heal_me.databinding.FragmentHomeBinding
import com.example.heal_me.model.SymptomItems
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Math.abs

class HomeFragment : Fragment() {

    private lateinit var handler: Handler
    private lateinit var homeAdvViewPager: ViewPager2
    private lateinit var doctorsInfoAdapter: DoctorsInfoAdapter


    private val homeAdvAdapter = HomeAdvAdapter(
        listOf(
            HomeAdvData(
                "Covid 19",
                "Protect your self and your family from \ncovid 19",
                R.drawable.doctor_pointing
            ),
            HomeAdvData(
                "Covid 19",
                "Protect your self and your family from \ncovid 19",
                R.drawable.doctor_pointing
            ),
            HomeAdvData(
                "Covid 19",
                "Protect your self and your family from \ncovid 19",
                R.drawable.doctor_pointing
            ),
            HomeAdvData(
                "Covid 19",
                "Protect your self and your family from \ncovid 19",
                R.drawable.doctor_pointing
            ),
            HomeAdvData(
                "Covid 19",
                "Protect your self and your family from \ncovid 19",
                R.drawable.doctor_pointing
            ),
            HomeAdvData(
                "Covid 19",
                "Protect your self and your family from \ncovid 19",
                R.drawable.doctor_pointing
            )
        )
    )


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var arrayList:ArrayList<SymptomItems> ? = null
    private var gridView:GridView ? = null
    private var symptomsAdapter:SymptomsAdapters ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeAdvViewPager = binding.viewPagerAd

        doctorsInfoAdapter = DoctorsInfoAdapter()

        val doctorsInfoObject = mutableListOf<DoctorsInfoDataItem>()
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))

        doctorsInfoAdapter.submitList(doctorsInfoObject)
        binding.rvDoctorsInfo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = doctorsInfoAdapter
        }


        binding.doctorsSeeAll.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_doctorsFragment)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        }

        binding.symptomsSeeAll.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_symptomsFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE

        }

        binding.setting.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_settingFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        }

        binding.wallet.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_walletFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        }


        handler = Handler(Looper.myLooper()!!)
        homeAdvViewPager.adapter = homeAdvAdapter
        homeAdvViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }
            })

        homeAdvViewPager.offscreenPageLimit = 3
        homeAdvViewPager.clipToPadding = false
        homeAdvViewPager.clipChildren = false
        homeAdvViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        homeAdvViewPager.setPageTransformer(transformer)

        gridView = binding.gridView
        arrayList = setDataList()
        symptomsAdapter = SymptomsAdapters(requireContext(),arrayList!!)
        gridView?.adapter = symptomsAdapter

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable,2000)
    }

    val runnable = Runnable {
        homeAdvViewPager.currentItem = homeAdvViewPager.currentItem + 1
    }

    private fun setDataList() : ArrayList<SymptomItems>{
        var arrayList:ArrayList<SymptomItems> = ArrayList()

        arrayList.add(SymptomItems(R.drawable.cold,"Cold"))
        arrayList.add(SymptomItems(R.drawable.anxiety,"Headache"))
        arrayList.add(SymptomItems(R.drawable.intestine,"Digestion"))
        arrayList.add(SymptomItems(R.drawable.ear_ache,"Ear Pain"))
        arrayList.add(SymptomItems(R.drawable.esophagus,"Throat"))
        arrayList.add(SymptomItems(R.drawable.lungs,"Lungs"))

        return arrayList
    }

}