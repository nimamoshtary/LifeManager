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
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.DialogGoalsBinding
import ir.lifeplas.lifemanager.databinding.FragmentGoalsShorttermBinding
import ir.lifeplas.lifemanager.dataclass.GoalsItem
import java.text.SimpleDateFormat
import java.util.Date


class GoalsShorttermFragment : Fragment() {
    lateinit var binding : FragmentGoalsShorttermBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGoalsShorttermBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("yyyyMMdd")
        val currentDateandTime: String = sdf.format(Date())

        val tableGoals = database.getdb(view.context).goalsDao
        val data = tableGoals.getAllShortterm()
        val adap = AdapterGoalsR(ArrayList(data))
        binding.RcycleYear.adapter = adap
        binding.RcycleYear.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        binding.floatingActionButton.setOnClickListener {
            val alert = AlertDialog.Builder(context).create()
            val dialogs = DialogGoalsBinding.inflate(layoutInflater)
            alert.setView(dialogs.root)
            alert.setCancelable(true)
            dialogs.radioGroup.check(R.id.radioButton3)
            alert.show()
            dialogs.btnok.setOnClickListener {
                val txtname = dialogs.nameofaction.text.toString()
                val txtinfo = dialogs.infoofaction.text.toString()
                var imp = 2
                val checked = dialogs.radioGroup.checkedRadioButtonId
                when(checked){
                    R.id.radioButton -> {imp = 4}
                    R.id.radioButton2 -> {imp = 3}
                    R.id.radioButton3 -> {imp = 2}
                    R.id.radioButton4 -> {imp = 1}
                }
                val datebi = currentDateandTime

                val Actionha= GoalsItem(
                    textTitle = txtname,
                    textsub = txtinfo,
                    datebild = datebi.toInt(),
                    model = 1,
                    importance = imp,
                    urgency = 1,
                    acions = 0,
                    fillaction = 0,
                    notfillaction = 0,
                    completion = 0)
                tableGoals.insert(Actionha)
                val datai = tableGoals.getAllShortterm()
                val adapt = AdapterGoalsR(ArrayList(datai))
                binding.RcycleYear.adapter = adapt
                binding.RcycleYear.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
                alert.dismiss()

                binding.RcycleYear.smoothScrollToPosition(0)
            }
        }
    }
}