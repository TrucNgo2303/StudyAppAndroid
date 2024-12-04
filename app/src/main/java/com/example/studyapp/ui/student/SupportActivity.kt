package com.example.studyapp.ui.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.studyapp.R
import com.google.android.material.navigation.NavigationView

class SupportActivity : AppCompatActivity() {
    private lateinit var tvSpType : AppCompatTextView

    var data_SpType = listOf("Hỗ trợ hệ thống", "Hỗ trợ lớp học", "Hỗ trợ tư vấn môn học")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        tvSpType = findViewById(R.id.tv_sp_type)


        //Chọn loại hỗ trợ
        tvSpType.setOnClickListener {
            chooseWaringLevel(tvSpType,data_SpType)
        }



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
    }
    private fun chooseWaringLevel(tv: AppCompatTextView, data: List<String>) {

        val popupMenu = PopupMenu(this, tv)

        // Add menu items from the list
        data.forEachIndexed { index, warningLevel ->
            popupMenu.menu.add(0, index, index, warningLevel)
        }


        // Handle menu item clicks
        popupMenu.setOnMenuItemClickListener { menuItem ->
            val selectedLevel = data[menuItem.itemId]
            tv.text =
                selectedLevel // Set the button text to the selected item
            true
        }
        popupMenu.show()
    }
}