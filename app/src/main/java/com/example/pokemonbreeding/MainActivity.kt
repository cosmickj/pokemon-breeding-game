package com.example.pokemonbreeding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pokemonbreeding.databinding.ActivityMainBinding
import com.example.pokemonbreeding.fragments.FireFragment
import com.example.pokemonbreeding.fragments.GrassFragment
import com.example.pokemonbreeding.fragments.WaterFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var grassFragment: GrassFragment
    private lateinit var fireFragment: FireFragment
    private lateinit var waterFragment: WaterFragment

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNav.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

        waterFragment = WaterFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, waterFragment).commit()

        fireFragment = FireFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, fireFragment).commit()

        grassFragment = GrassFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, grassFragment).commit()

        binding.coachMarkCloseBtn.setOnClickListener {
            binding.mainCoachMark.visibility = View.GONE
        }

    }

    // 바텀네비게이션 아이템 클릭 리스너 설정
    private val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.type_grass -> {
                supportFragmentManager.beginTransaction().show(grassFragment).commit()
                supportFragmentManager.beginTransaction().hide(fireFragment).commit()
                supportFragmentManager.beginTransaction().hide(waterFragment).commit()
            }
            R.id.type_fire -> {
                supportFragmentManager.beginTransaction().hide(grassFragment).commit()
                supportFragmentManager.beginTransaction().show(fireFragment).commit()
                supportFragmentManager.beginTransaction().hide(waterFragment).commit()
            }
            R.id.type_water -> {
//                waterFragment = WaterFragment.newInstance()
//                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, waterFragment).commit()
                supportFragmentManager.beginTransaction().hide(grassFragment).commit()
                supportFragmentManager.beginTransaction().hide(fireFragment).commit()
                supportFragmentManager.beginTransaction().show(waterFragment).commit()
            }
        }
        true
    }

    // 종료 여부 물어보기
    override fun onBackPressed() {
        val eBuilder = AlertDialog.Builder(this)
        eBuilder.setTitle("포켓몬 레벨이 초기화 됩니다.")
        eBuilder.setMessage("정말 종료하시겠습니까?")
        eBuilder.setPositiveButton("네") {
                Dialog,which ->
            finish()
        }
        eBuilder.setNegativeButton("아니오", null)
        eBuilder.show()
    }

    override fun onStart() {
        super.onStart()
        binding.mainCoachMark.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        val sdf = SimpleDateFormat("yyyy/M/dd hh:mm:ss")
        val currentDate = sdf.format(Date())
        Toast.makeText(this, "종료 시간은 $currentDate 입니다.", Toast.LENGTH_LONG).show()

    }
}