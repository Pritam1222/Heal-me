package com.example.heal_me

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.heal_me.adapters.IntroSlideAdapter
import com.example.heal_me.data.IntroSlide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IntroActivity : AppCompatActivity() {

    private val introSlideAdapter = IntroSlideAdapter(
        listOf(
            IntroSlide(
                "Find the best doctor \n" +
                        "near you",
                "With the help of our algorithms,\n" +
                        " now locate the best doctors around your vicinity at total ease.",
                R.drawable.doctors_pana

            ),
            IntroSlide(
                "Schedule appointments with \n" +
                        "expert doctors",
                "Find experienced specialist doctors with\n" +
                        "expert ratings and reviews and book your appointments hassle-free",
                R.drawable.ophthalmologist
            ),
            IntroSlide(
                "Get best offer for \n" +
                        "your next appointment",
                "Get addicted to memes and comic content for a light-hearted take on daily events. Toggle between feeds to alternate between serious and fun content.",
                R.drawable.gift_card_bro
            ),
            IntroSlide(
                "Documents",
                "Maintain all your prescriptions, Reports and Invoice at one place.",
                R.drawable.file_synchronization_pana
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prevent screenshots from being taken
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        setContentView(R.layout.app_intro)
        val introSliderViewPager = findViewById<ViewPager2>(R.id.introSliderViewPager)
        introSliderViewPager.adapter = introSlideAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        val textSkipIntro = findViewById<TextView>(R.id.textSkipIntro)
        val buttonNext = findViewById<FloatingActionButton>(R.id.buttonNext)
        buttonNext.setOnClickListener{
            if (introSliderViewPager.currentItem + 1 < introSlideAdapter.itemCount){
                introSliderViewPager.currentItem += 1
            }else{
                Intent(applicationContext, LoginActivity::class.java).also{
                    startActivity(it)
                    finish()
                }
            }
        }
        textSkipIntro.setOnClickListener {
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

    }
    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(10,0,10,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            val indicatorsContainer = findViewById<LinearLayout>(R.id.indicatorsContainer)
            indicatorsContainer.addView(indicators[i])
        }
    }
    private fun setCurrentIndicator(index: Int){
        val indicatorsContainer = findViewById<LinearLayout>(R.id.indicatorsContainer)
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

}