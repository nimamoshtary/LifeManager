package ir.lifeplas.lifemanager.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.lifeplas.lifemanager.fragment.ActionsDayFragment
import ir.lifeplas.lifemanager.fragment.ActionsMonthFragment
import ir.lifeplas.lifemanager.fragment.ActionsYearFragment

class AdapterActionsV(fragment:Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int) :Fragment {
        when (position) {
            0 -> {
                return ActionsDayFragment()
            }
            1 -> {
                return ActionsMonthFragment()
            }
            2 -> {
                return ActionsYearFragment()
            }
            else -> {
                return Fragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

}