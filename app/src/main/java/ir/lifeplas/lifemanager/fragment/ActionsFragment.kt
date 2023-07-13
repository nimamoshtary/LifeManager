package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ir.lifeplas.lifemanager.Adapters.AdapterActionsV
import ir.lifeplas.lifemanager.databinding.FragmentActionsBinding

class ActionsFragment : Fragment(){
    lateinit var binding: FragmentActionsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentActionsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adap = AdapterActionsV(this)
        binding.viewpager.adapter = adap

        val mediator = TabLayoutMediator(binding.tablayout,binding.viewpager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    when(position){
                        0 -> {tab.text = " year "}
                        1 -> {tab.text = " month "}
                        2 -> {tab.text = " day "}
                    }
                }
            })
        mediator.attach()
    }
}