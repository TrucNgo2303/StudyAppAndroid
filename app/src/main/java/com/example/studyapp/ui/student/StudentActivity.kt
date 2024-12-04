package com.example.studyapp.ui.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.studyapp.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import me.relex.circleindicator.CircleIndicator3

class StudentActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentPageAdapter

    private lateinit var viewPager: ViewPager2
    private lateinit var handler: Handler
    private lateinit var adapterBanner: BannerAdapter
    private var bannerList = listOf(R.drawable.sova, R.drawable.killjoy, R.drawable.fade)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        //Menu
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val menuIcon: AppCompatImageView = findViewById(R.id.img_menu_icon)

        menuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(applicationContext, StudentActivity::class.java)
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_forum -> {
                    val intent = Intent(applicationContext, ForumActivity::class.java)
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_support -> {
                    val intent = Intent(applicationContext, SupportActivity::class.java)
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }



        tabLayout = findViewById(R.id.tab_layout)
        //Banner khóa học nổi bật
        viewPager = findViewById(R.id.view_pager2_banner)
        val circleIndicator = findViewById<CircleIndicator3>(R.id.circle_indicator)

        adapterBanner = BannerAdapter(bannerList)
        viewPager.adapter = adapterBanner

        circleIndicator.setViewPager(viewPager)

        handler = Handler(mainLooper)
        startAutoSlide()


        //Tab khóa học
        viewPager2 = findViewById(R.id.view_pager2)
        adapter = FragmentPageAdapter(supportFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText("Tất cả khóa học"))
        tabLayout.addTab(tabLayout.newTab().setText("Khóa học của tôi"))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab !=null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
    private fun startAutoSlide() {
        val runnable = object : Runnable {
            override fun run() {
                val currentItem = viewPager.currentItem
                val nextItem = (currentItem + 1) % bannerList.size
                viewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 3000) // Lặp lại sau 3 giây
            }
        }
        handler.postDelayed(runnable, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}