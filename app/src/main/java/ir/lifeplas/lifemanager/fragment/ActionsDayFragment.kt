package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.Actionsdao
import ir.lifeplas.lifemanager.Adapters.AdapterActionsR
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.DialogActionsBinding
import ir.lifeplas.lifemanager.databinding.FragmentActionsDayBinding
import ir.lifeplas.lifemanager.dataclass.ActionsItem

class ActionsDayFragment : Fragment() {
    lateinit var binding : FragmentActionsDayBinding
    lateinit var tableAct : Actionsdao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentActionsDayBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tableAct = database.getdb(view.context).actionsDao
        val data = tableAct.getall()
        val adap = AdapterActionsR(ArrayList(data))
        binding.RcycleDay.adapter = adap
        binding.RcycleDay.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)

        binding.floatingActionButton.setOnClickListener {
            val alert = AlertDialog.Builder(view.context).create()
            val dialogs = DialogActionsBinding.inflate(layoutInflater)
            alert.setView(dialogs.root)
            alert.setCancelable(true)
            alert.show()
            dialogs.btnok.setOnClickListener {
                val txtname = dialogs.nameofaction.text.toString()
                val txtinfo = dialogs.infoofaction.text.toString()
                val datebi = 28

                val Actionha = ActionsItem(textTitle = txtname, textsub = txtinfo, datebild = datebi)
                adap.addAction(Actionha)
                tableAct.insert(Actionha)
                alert.dismiss()
                binding.RcycleDay.smoothScrollToPosition(0)
            }
        }
    }
}