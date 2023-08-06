package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ir.lifeplas.lifemanager.Adapters.AdapterActionsV
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.databinding.FragmentActionsBinding

class ActionsFragment : Fragment() {
    lateinit var binding: FragmentActionsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adap = AdapterActionsV(this)
        binding.viewpager.adapter = adap

        val mediator = TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            when (position) {
                0 -> { tab.setIcon(R.drawable.ic_baseline_event) }
                1 -> { tab.setIcon(R.drawable.ic_month_forground) }
                2 -> { tab.setIcon(R.drawable.ic_year_foreground) }
            }
        }
        mediator.attach()
    }
}