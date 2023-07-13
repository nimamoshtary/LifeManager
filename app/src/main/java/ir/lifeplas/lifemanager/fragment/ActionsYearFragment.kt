package ir.lifeplas.lifemanager.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.Adapters.AdapterActionsR
import ir.lifeplas.lifemanager.databinding.DialogActionsBinding
import ir.lifeplas.lifemanager.databinding.FragmentActionsYearBinding
import ir.lifeplas.lifemanager.dataclass.ActionsItem

class ActionsYearFragment : Fragment() {
    lateinit var binding : FragmentActionsYearBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentActionsYearBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listactions = arrayListOf<ActionsItem>(
            ActionsItem("فعالیت اول","توضیحات","@drawable/ic_more","@drawable/ic_complte", 28)
        )
        val adap = AdapterActionsR(listactions)
        binding.RcycleYears.adapter = adap
        binding.RcycleYears.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
    }
}