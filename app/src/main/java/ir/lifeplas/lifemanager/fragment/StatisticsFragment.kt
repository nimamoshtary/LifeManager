package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.lifeplas.lifemanager.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment(){
    lateinit var binding: FragmentStatisticsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStatisticsBinding.inflate(inflater,container,false)
        return binding.root
    }

}