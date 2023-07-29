package com.example.heal_me.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.adapters.DoctorsInfoAdapter
import com.example.heal_me.data.DoctorsInfoDataItem
import com.example.heal_me.databinding.FragmentDoctorsBinding

class DoctorsFragment : Fragment() {

    private lateinit var doctorsInfoAdapter: DoctorsInfoAdapter
    private var _binding: FragmentDoctorsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDoctorsBinding.inflate(inflater, container, false)
        doctorsInfoAdapter = DoctorsInfoAdapter()

        val doctorsInfoObject = mutableListOf<DoctorsInfoDataItem>()
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?w=1060&t=st=1676702466~exp=1676703066~hmac=103497a0eef6f4dd241c7c028c9ea8afa4dc0cd6cd1f1617edefdd56a5d90f92","Dr. Anna baker", "Heart surgeon", "350 m", 4.5f, 120))
        doctorsInfoObject.add(DoctorsInfoDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon", "500 m", 5f, 150))



        doctorsInfoAdapter.submitList(doctorsInfoObject)
        binding.rvDoctorsFragmentDoctorsInfo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = doctorsInfoAdapter
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}