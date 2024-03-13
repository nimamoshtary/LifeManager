package ir.lifeplas.lifemanager

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ir.lifeplas.lifemanager.Adapters.AdapterGoalsR
import ir.lifeplas.lifemanager.databinding.ActivityMainBinding
import ir.lifeplas.lifemanager.dataclass.GoalsItem
import ir.lifeplas.lifemanager.fragment.ActionsFragment
import ir.lifeplas.lifemanager.fragment.EditGoalsFragment
import ir.lifeplas.lifemanager.fragment.GoalsFragment
import ir.lifeplas.lifemanager.fragment.ScheduleFragment
import ir.lifeplas.lifemanager.fragment.SeeActionsFragment
import ir.lifeplas.lifemanager.fragment.SettingFragment
import ir.lifeplas.lifemanager.fragment.StatisticsFragment

class MainActivity : AppCompatActivity() , AdapterGoalsR.Transferdata {
    lateinit var binding : ActivityMainBinding
    val viewmodel : SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        firstRun()

        val drawetogle = ActionBarDrawerToggle(
            this,
            binding.drawerLayoutMain,
            binding.toolbar,
            R.string.app_name,
            R.string.app_name)
        binding.drawerLayoutMain.addDrawerListener(drawetogle)

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

    override fun clicked() {
        supportFragmentManager.beginTransaction().replace(R.id.mainframe, SeeActionsFragment() ).commit()
        binding.drawerLayoutMain.open()
    }

    override fun longcliked(goalsItem: GoalsItem,model:Int) {
        viewmodel.text.value = goalsItem.textTitle
        viewmodel.textsub.value = goalsItem.textsub
        viewmodel.view.value = goalsItem
        viewmodel.fra.value = model
        supportFragmentManager.beginTransaction().replace(R.id.mainframe, EditGoalsFragment() ).commit()
        binding.drawerLayoutMain.open()
    }
    fun closingdrawer(model:Int){
        GoalsFragment.modelstart = model
        replaceTra( GoalsFragment() )
        binding.drawerLayoutMain.close()
    }
}