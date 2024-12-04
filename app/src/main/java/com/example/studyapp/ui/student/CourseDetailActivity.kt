package com.example.studyapp.ui.student

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R
import com.google.android.material.navigation.NavigationView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class CourseDetailActivity : AppCompatActivity() {
    private var isFullscreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        //Video
        val youTubePlayerView: YouTubePlayerView = findViewById(R.id.wv_course_trailer)
        val btnToggleFullscreen: Button = findViewById(R.id.btn_toggle_fullscreen)

        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "su5_dkUlEqI"
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        btnToggleFullscreen.setOnClickListener {
            toggleFullscreen(youTubePlayerView)
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


        val lessons = listOf(
            Lesson("Bài học 1", "45:00"),
            Lesson("Bài học 2", "45:00"),
            Lesson("Bài học 3", "45:00"),
            Lesson("Bài học 4", "45:00"),
            Lesson("Bài học 5", "45:00"),
            Lesson("Bài học 6", "45:00"),
            Lesson("Bài học 7", "45:00"),
            Lesson("Bài học 8", "45:00"),
            Lesson("Bài học 9", "45:00"),
            Lesson("Bài học 10", "45:00"),
            Lesson("Bài học 11", "45:00"),
            Lesson("Bài học 12", "45:00"),
            Lesson("Bài học 13", "45:00"),
            Lesson("Bài học 14", "45:00"),
            )


        val recyclerView: RecyclerView = findViewById(R.id.rcv_lesson)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LessonAdapter(lessons)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
    override fun onDestroy() {
        super.onDestroy()
        findViewById<YouTubePlayerView>(R.id.wv_course_trailer).release()
    }
    private fun toggleFullscreen(youTubePlayerView: YouTubePlayerView) {
        isFullscreen = !isFullscreen

        if (isFullscreen) {
            supportActionBar?.hide()
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            youTubePlayerView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            youTubePlayerView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        } else {
            supportActionBar?.show()
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            youTubePlayerView.layoutParams.height = resources.getDimensionPixelSize(R.dimen.youtube_player_default_height)
            youTubePlayerView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

}