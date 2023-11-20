package ir.lifeplas.lifemanager.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.lifeplas.lifemanager.fragment.GoalsLongtimeFragment
import ir.lifeplas.lifemanager.fragment.GoalsMidtermFragment
import ir.lifeplas.lifemanager.fragment.GoalsShorttermFragment

class AdapterGoalsV(fragment:Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int) :Fragment {
        when (position) {
            0 -> {
                return GoalsLongtimeFragment()
            }
            1 -> {
                return GoalsMidtermFragment()
            }
            2 -> {
                return GoalsShorttermFragment()
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