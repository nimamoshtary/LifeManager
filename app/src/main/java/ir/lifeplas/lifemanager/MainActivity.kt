package ir.lifeplas.lifemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ir.lifeplas.lifemanager.databinding.ActivityMainBinding
import ir.lifeplas.lifemanager.fragment.*

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        firstRun()
        binding.navigationBottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_bottem_Actions->{ replaceTra( ActionsFragment() ) }
                R.id.nav_bottam_Goals->{ replaceTra( GoalsFragment() ) }
                R.id.nav_bottom_Schedule->{ replaceTra( ScheduleFragment() ) }
                R.id.nav_bottom_Statistics->{ replaceTra( StatisticsFragment() ) }
                R.id.nav_bottom_Setting->{ replaceTra( SettingFragment() ) }
            }
            true
        }
    }
    private fun replaceTra(fra:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.FrameLayout,fra)
        transaction.commit()
    }
    fun firstRun(){
        replaceTra( ActionsFragment() )
        binding.navigationBottom.selectedItemId = R.id.nav_bottem_Actions
    }
}