package com.example.studyapp.ui.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R
import com.google.android.material.navigation.NavigationView

class ForumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)

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
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }







        val forums = listOf(
            Forum("Bài Kiểm tra đề quá khó", "Nguyễn Văn A", "1.000.000"),
            Forum("Chấm bài quá muộn", "Nguyễn Văn B", "2.000.000"),
            Forum("Giảng viên ko tốt", "Nguyễn Văn C", "3.000.000"),
            Forum("Nên học ai nhất", "Nguyễn Văn D", "10.000.000"),
            Forum("App quá lag", "Nguyễn Văn E", "500.000"),
            Forum("Học Toán ai bây giờ", "Nguyễn Văn F", "300.000"),
            Forum("Giảng viên ko tốt", "Nguyễn Văn C", "3.000.000"),
            Forum("Giảng viên ko tốt", "Nguyễn Văn C", "3.000.000"),
            Forum("Giảng viên ko tốt", "Nguyễn Văn C", "3.000.000"),
            Forum("Giảng viên ko tốt", "Nguyễn Văn C", "3.000.000"),
            Forum("Giảng viên ko tốt", "Nguyễn Văn C", "3.000.000"),

            )


        val recyclerView: RecyclerView = findViewById(R.id.rcv_forum)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = ForumAdapter(forums)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}