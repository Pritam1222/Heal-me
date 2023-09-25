package com.example.heal_me.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.heal_me.R
import com.example.heal_me.adapters.DoctorsInfoAdapter
import com.example.heal_me.adapters.HomeAdvAdapter
import com.example.heal_me.adapters.SymptomsAdapters
import com.example.heal_me.data.Doctors
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.data.HomeAdvData
import com.example.heal_me.databinding.FragmentHomeBinding
import com.example.heal_me.model.SymptomItems
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Math.abs


class HomeFragment : Fragment() {

    private var arrayList:ArrayList<SymptomItems> ? = null
    private var gridView:GridView ? = null
    private var symptomsAdapter:SymptomsAdapters ? = null

    private lateinit var binding : FragmentHomeBinding
    private lateinit var handler: Handler
    private lateinit var homeAdvViewPager: ViewPager2
    private lateinit var doctorsInfoAdapter: DoctorsInfoAdapter
    private lateinit var doctorsDatabase: DoctorsDatabase
    private var bottomNavigationView: BottomNavigationView? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        homeAdvViewPager = binding.viewPagerAd

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        GlobalScope.launch {
            try {
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(1,"Dr. Naresh Trehan", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Heart Surgeon","4 years", 4.5, 500, 100, 1000,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(2,"Dr. Yugal K Mishra", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Heart Surgeon","5 years", 4.3, 600, 110, 1100,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(3,"Dr. Ramakanta Panda", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Heart Surgeon","6 years", 4.0, 700, 120, 1200,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(4,"Dr. John B Watson", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Heart Surgeon","3 years", 4.8, 800, 130, 1300,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(5,"Dr. Carl Rogers", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Psychologist","4 years", 4.5, 500, 100, 1000,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(6,"Dr. Sigmund Freud", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Psychologist","5 years", 4.3, 600, 110, 1100,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(7,"Dr. Abraham Maslow", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Psychologist","6 years", 4.0, 700, 120, 1200,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(8,"Dr. Albert Bandura", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Psychologist","3 years", 4.8, 800, 130, 1300,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(9,"Dr. Alfred Adler", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Dentist","4 years", 4.5, 500, 100, 1000,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(10,"Dr. Carl Jung", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Dentist","5 years", 4.3, 600, 110, 1100,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(11,"Dr. William James", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Dentist","6 years", 4.0, 700, 120, 1200,false))
                doctorsDatabase.doctorsDao().insertDoctor(Doctors(12,"Dr. Wilhelm Wundt", "https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92", "Dentist","3 years", 4.8, 800, 130, 1300,false))

            } catch (e: Exception) {
                e.printStackTrace()
                // Log or handle the exception as needed
            }
        }

        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation)
        doctorsInfoAdapter = DoctorsInfoAdapter(bottomNavigationView!!)

        binding.cvDocSpecialty1.setCardBackgroundColor(Color.parseColor("#089BAB"))
        binding.ivBackgroundDocSpecialty1.setColorFilter(Color.parseColor("#FFFFFF"))
        binding.tvDocSpecialty1.setTextColor(Color.parseColor("#FFFFFF"))

        doctorsDatabase.doctorsDao().getDoctorBySpecialty("Heart Surgeon").observe(viewLifecycleOwner) { data ->
            doctorsInfoAdapter.submitList(data)
        }

        binding.cvDocSpecialty1.setOnClickListener{
            binding.cvDocSpecialty1.setCardBackgroundColor(Color.parseColor("#089BAB"))
            binding.ivBackgroundDocSpecialty1.setColorFilter(Color.parseColor("#FFFFFF"))
            binding.tvDocSpecialty1.setTextColor(Color.parseColor("#FFFFFF"))

            binding.cvDocSpecialty2.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.ivBackgroundDocSpecialty2.setColorFilter(Color.parseColor("#089BAB"))
            binding.tvDocSpecialty2.setTextColor(Color.parseColor("#000000"))

            binding.cvDocSpecialty3.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.ivBackgroundDocSpecialty3.setColorFilter(Color.parseColor("#089BAB"))
            binding.tvDocSpecialty3.setTextColor(Color.parseColor("#000000"))

            doctorsDatabase.doctorsDao().getDoctorBySpecialty("Heart Surgeon").observe(viewLifecycleOwner) { data ->
                doctorsInfoAdapter.submitList(data)
            }
        }

        binding.cvDocSpecialty2.setOnClickListener{
            binding.cvDocSpecialty1.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.ivBackgroundDocSpecialty1.setColorFilter(Color.parseColor("#089BAB"))
            binding.tvDocSpecialty1.setTextColor(Color.parseColor("#000000"))

            binding.cvDocSpecialty2.setCardBackgroundColor(Color.parseColor("#089BAB"))
            binding.ivBackgroundDocSpecialty2.setColorFilter(Color.parseColor("#FFFFFF"))
            binding.tvDocSpecialty2.setTextColor(Color.parseColor("#FFFFFF"))

            binding.cvDocSpecialty3.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.ivBackgroundDocSpecialty3.setColorFilter(Color.parseColor("#089BAB"))
            binding.tvDocSpecialty3.setTextColor(Color.parseColor("#000000"))

            doctorsDatabase.doctorsDao().getDoctorBySpecialty("Psychologist").observe(viewLifecycleOwner) { data ->
                doctorsInfoAdapter.submitList(data)
            }
        }

        binding.cvDocSpecialty3.setOnClickListener{
            binding.cvDocSpecialty1.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.ivBackgroundDocSpecialty1.setColorFilter(Color.parseColor("#089BAB"))
            binding.tvDocSpecialty1.setTextColor(Color.parseColor("#000000"))

            binding.cvDocSpecialty2.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.ivBackgroundDocSpecialty2.setColorFilter(Color.parseColor("#089BAB"))
            binding.tvDocSpecialty2.setTextColor(Color.parseColor("#000000"))

            binding.cvDocSpecialty3.setCardBackgroundColor(Color.parseColor("#089BAB"))
            binding.ivBackgroundDocSpecialty3.setColorFilter(Color.parseColor("#FFFFFF"))
            binding.tvDocSpecialty3.setTextColor(Color.parseColor("#FFFFFF"))

            doctorsDatabase.doctorsDao().getDoctorBySpecialty("Dentist").observe(viewLifecycleOwner) { data ->
                doctorsInfoAdapter.submitList(data)
            }
        }


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