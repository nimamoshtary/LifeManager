package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ir.lifeplas.lifemanager.Adapters.AdapterGoalsV
import ir.lifeplas.lifemanager.databinding.FragmentGoalsBinding

class GoalsFragment : Fragment(){
    lateinit var binding: FragmentGoalsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGoalsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adap = AdapterGoalsV(this)
        binding.viewpager.adapter = adap

        val mediator = TabLayoutMediator(binding.tablayout,binding.viewpager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    when(position){
                        0 -> {tab.text = " Long Time "}
                        1 -> {tab.text = " Year "}
                        2 -> {tab.text = " Month "}
                        3 -> {tab.text = " Week "}
                    }
                }
            })
        mediator.attach()
    }
}