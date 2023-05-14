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
import ir.lifeplas.lifemanager.databinding.FragmentActionsDayBinding
import ir.lifeplas.lifemanager.databinding.FragmentActionsMonthBinding
import ir.lifeplas.lifemanager.dataclass.ActionsItem

class ActionsDayFragment : Fragment() {
    lateinit var binding : FragmentActionsDayBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentActionsDayBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listactions = arrayListOf<ActionsItem>(
            ActionsItem("فعالیت اول","توضیحات","@drawable/ic_more","@drawable/ic_complte")
        )
        val adap = AdapterActionsR(listactions)
        binding.RcycleDay.adapter = adap
        binding.RcycleDay.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        binding.floatingActionButton.setOnClickListener {
            val alert = AlertDialog.Builder(context).create()
            val dialogs = DialogActionsBinding.inflate(layoutInflater)
            alert.setView(dialogs.root)
            alert.setCancelable(true)
            alert.show()
            dialogs.btnok.setOnClickListener {
                val txtname = dialogs.nameofaction.text.toString()
                val txtinfo = dialogs.infoofaction.text.toString()
                val imgmore = listactions[0].imagemore
                val imgcomplet = listactions[0].imagefil

                val Action = ActionsItem(txtname, txtinfo, imgmore, imgcomplet)
                adap.addAction(Action)
                alert.dismiss()
                binding.RcycleDay.smoothScrollToPosition(0)
            }

        }
    }
}