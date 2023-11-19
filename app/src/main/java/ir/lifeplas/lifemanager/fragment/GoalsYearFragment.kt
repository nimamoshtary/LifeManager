package ir.lifeplas.lifemanager.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.Adapters.AdapterGoalsR
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.DialogActionsBinding
import ir.lifeplas.lifemanager.databinding.FragmentGoalsYearBinding
import ir.lifeplas.lifemanager.dataclass.GoalsItem

class GoalsYearFragment : Fragment() {
    lateinit var binding : FragmentGoalsYearBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentGoalsYearBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tableGoals = database.getdb(view.context).goalsDao
        val data = tableGoals.getAllGoals()
        val adap = AdapterGoalsR(ArrayList(data))
        binding.RcycleYear.adapter = adap
        binding.RcycleYear.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        binding.floatingActionButton.setOnClickListener {
            val alert = AlertDialog.Builder(context).create()
            val dialogs = DialogActionsBinding.inflate(layoutInflater)
            alert.setView(dialogs.root)
            alert.setCancelable(true)
            alert.show()
            dialogs.btnok.setOnClickListener {
                val txtname = dialogs.nameofaction.text.toString()
                val txtinfo = dialogs.infoofaction.text.toString()
                val datebi = 28

                val Actionha= GoalsItem(textTitle = txtname, textsub = txtinfo, datebild = datebi)
                adap.addAction(Actionha)
                tableGoals.insert(Actionha)
                alert.dismiss()

                binding.RcycleYear.smoothScrollToPosition(0)
            }

        }
    }
}