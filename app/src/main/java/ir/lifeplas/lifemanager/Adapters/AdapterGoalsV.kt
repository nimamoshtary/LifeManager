package ir.lifeplas.lifemanager.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.lifeplas.lifemanager.fragment.*

class AdapterGoalsV(fragment:Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int) :Fragment {
        return when (position) {
            0 -> {
                return GoalsLongtimeFragment()
            }
            1 -> {
                return GoalsYearFragment()
            }
            2 -> {
                return GoalsMonthFragment()
            }
            3 -> {
                return GoalsWeekFragment()
            }
            else -> {
                return Fragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

}