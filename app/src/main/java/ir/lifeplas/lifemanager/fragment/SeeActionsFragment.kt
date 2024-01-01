package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.Adapters.AdapterActionsRH
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.FragmentSeeActionsBinding

class SeeActionsFragment : Fragment() {
    lateinit var binding : FragmentSeeActionsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSeeActionsBinding.inflate(layoutInflater, null, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tableAct = database.getdb(view.context).actionsDao
        val data = tableAct.getAllActions()
        val adap = AdapterActionsRH(ArrayList(data))
        binding.RcycleHeader.adapter = adap
        binding.RcycleHeader.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)

    }
}