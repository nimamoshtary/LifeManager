package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.Actionsdao
import ir.lifeplas.lifemanager.Adapters.AdapterActionsR
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.FragmentActionsMonthBinding

class ActionsMonthFragment : Fragment() {
    lateinit var binding : FragmentActionsMonthBinding
    lateinit var tableAct : Actionsdao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentActionsMonthBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tableAct = database.getdb(view.context).actionsDao
        showall()
//        val listactions = arrayListOf<ActionsItem>(
//            ActionsItem("فعالیت اول","توضیحات","@drawable/ic_more","@drawable/ic_complte", 28)
//        )
//        val adap = AdapterActionsR(listactions)
//        binding.RcycleMonth.adapter = adap
//        binding.RcycleMonth.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
    }
    private fun showall(){
        val data = tableAct.getall()
        val adap = AdapterActionsR(ArrayList(data))
        binding.RcycleMonth.adapter = adap
        binding.RcycleMonth.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
    }
}