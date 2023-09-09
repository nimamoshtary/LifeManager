package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.lifeplas.lifemanager.databinding.FragmentGoalsLongtimeBinding

class GoalsLongtimeFragment : Fragment() {
    lateinit var binding : FragmentGoalsLongtimeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentGoalsLongtimeBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

}