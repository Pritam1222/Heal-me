package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.R
import com.example.heal_me.data.Doctors
import com.example.heal_me.databinding.DoctorsInfoItemViewBinding
import com.example.heal_me.fragments.DoctorsFragmentDirections
import com.example.heal_me.fragments.FavouriteDoctorFragmentDirections
import com.example.heal_me.fragments.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class DoctorsInfoAdapter(private val bottomNavigationView: BottomNavigationView) : ListAdapter<Doctors, DoctorsInfoAdapter.DoctorsInfoViewHolder>(DoctorsInfoAdapter.DATA_COMPARATOR){

    private lateinit var navController : NavController

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Doctors>() {
            override fun areItemsTheSame(
                oldItem: Doctors,
                newItem: Doctors
            ): Boolean = oldItem.image == newItem.image

            override fun areContentsTheSame(
                oldItem: Doctors,
                newItem: Doctors
            ): Boolean = oldItem == newItem

        }
    }

    class DoctorsInfoViewHolder(private val doctorsInfoItemViewBinding: DoctorsInfoItemViewBinding): RecyclerView.ViewHolder(doctorsInfoItemViewBinding.root){
        fun bind(doctorsInfoDataItem: Doctors){
            Glide.with(doctorsInfoItemViewBinding.root)
                .load(doctorsInfoDataItem.image)
                .into(doctorsInfoItemViewBinding.ivDoctorsInfoImage)
            doctorsInfoItemViewBinding.tvDoctorsInfoName.text = doctorsInfoDataItem.name
            doctorsInfoItemViewBinding.tvDoctorsInfoSpeciality.text = doctorsInfoDataItem.specialist
            doctorsInfoItemViewBinding.tvDoctorsInfoRatings.text = doctorsInfoDataItem.rating.toString()
            doctorsInfoItemViewBinding.tvDoctorsInfoReviews.text = doctorsInfoDataItem.reviews.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsInfoViewHolder {
        return DoctorsInfoViewHolder(DoctorsInfoItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DoctorsInfoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        val selectedItemId = getItem(position)?.id

        holder.itemView.setOnClickListener {
            val navHostFragment = (holder.itemView.context as FragmentActivity).supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            when (navController.currentDestination?.id) {
                R.id.homeFragment2 -> {
                    val action: NavDirections = HomeFragmentDirections.actionHomeFragment2ToDoctorDescriptionFragment(
                        selectedItemId!!
                    )
                    navController.navigate(action)
                }
                R.id.doctorsFragment -> {
                    val action: NavDirections = DoctorsFragmentDirections.actionDoctorsFragmentToDoctorDescriptionFragment(
                        selectedItemId!!
                    )
                    navController.navigate(action)
                }
                R.id.favouriteDoctorFragment -> {
                    val action: NavDirections = FavouriteDoctorFragmentDirections.actionFavouriteDoctorFragmentToDoctorDescriptionFragment(
                        selectedItemId!!
                    )
                    navController.navigate(action)
                }
            }

            // Hide the BottomNavigationView
            bottomNavigationView.visibility = View.GONE

        }
    }

}
