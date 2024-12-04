package com.example.studyapp.ui.student

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyCourseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mListener: CourseAdapter.onItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_course, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rcv_all_course)

        val courseList = listOf(
            Course(R.drawable.jett, "Khóa học Toán", "120,000 VND"),
            Course(R.drawable.killjoy, "Khóa học Lý", "150,000 VND"),
            Course(R.drawable.fade, "Khóa học Hóa", "200,000 VND"),
            Course(R.drawable.sova, "Khóa học Văn", "180,000 VND"),
            Course(R.drawable.phoenix, "Khóa học Anh", "220,000 VND")
        )

        // Khởi tạo Adapter
        val adapter = CourseAdapter(courseList)

        // Thiết lập listener cho item click
        adapter.setOnItemClickListener(object : CourseAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                // Xử lý sự kiện khi người dùng nhấp vào item
                val clickedCourse = courseList[position]

                // Hiển thị một Toast để xác nhận khóa học đã chọn
                Toast.makeText(requireContext(), "Bạn đã chọn: ${clickedCourse.courseName}", Toast.LENGTH_SHORT).show()

                // Tạo Intent để chuyển đến màn hình CourseDetailActivity
                val intent = Intent(requireContext(), CourseDetailActivity::class.java)

                // Truyền dữ liệu khóa học đã chọn
                intent.putExtra("COURSE_NAME", clickedCourse.courseName)
                intent.putExtra("COURSE_PRICE", clickedCourse.coursePrice)
                intent.putExtra("COURSE_IMAGE", clickedCourse.resourceImage)
                // Chuyển đến màn hình CourseDetailActivity
                startActivity(intent)
            }
        })

        // Thiết lập RecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyCourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}