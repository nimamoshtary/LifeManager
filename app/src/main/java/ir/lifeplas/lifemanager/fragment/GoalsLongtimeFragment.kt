package ir.lifeplas.lifemanager.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.Adapters.AdapterGoalsR
import ir.lifeplas.lifemanager.MainActivity
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.DialogGoalsBinding
import ir.lifeplas.lifemanager.databinding.FragmentGoalsLongtimeBinding
import ir.lifeplas.lifemanager.dataclass.GoalsItem
import java.text.SimpleDateFormat
import java.util.Date

class GoalsLongtimeFragment : Fragment() , AdapterGoalsR.Transferdata{
    lateinit var binding : FragmentGoalsLongtimeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGoalsLongtimeBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("yyyyMMdd")
        val currentDateandTime: String = sdf.format(Date())

        var tableGoals = database.getdb(view.context).goalsDao
        val data = tableGoals.getAllLongtime()
        val adap = AdapterGoalsR(ArrayList(data),view.context,this)
        binding.RcyclerLong.adapter = adap
        binding.RcyclerLong.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        binding.floatingActionButton.setOnClickListener {
            val alert = AlertDialog.Builder(context).create()
            val dialogs = DialogGoalsBinding.inflate(layoutInflater)
            alert.setView(dialogs.root)
            alert.setCancelable(true)
            dialogs.radioGroup.check(R.id.radioButton3)
            dialogs.radiogroupf.check(R.id.rbNormally)
            alert.show()
            dialogs.btnok.setOnClickListener {
                val txtname = dialogs.nameofaction.editText?.text.toString()
                val txtinfo = dialogs.infoofaction.editText?.text.toString()
                var imp = 2
                val checked = dialogs.radioGroup.checkedRadioButtonId
                when(checked){
                    R.id.radioButton -> {imp = 4}
                    R.id.radioButton2 -> {imp = 3}
                    R.id.radioButton3 -> {imp = 2}
                    R.id.radioButton4 -> {imp = 1}
                }
                var urg = 1
                val checkedu = dialogs.radiogroupf.checkedRadioButtonId
                when(checkedu){
                    R.id.rbUrgency -> urg = 3
                    R.id.rbNUrgency -> urg = 2
                    R.id.rbNormally -> urg = 1
                }
                val datebi = currentDateandTime

                val Actionha= GoalsItem(
                    textTitle = txtname,
                    textsub = txtinfo,
                    datebild = datebi.toInt(),
                    model = 3,
                    importance = imp,
                    urgency = urg,
                    acions = 0,
                    fillaction = 0,
                    notfillaction = 0,
                    completion = 0 )
                tableGoals.insert(Actionha)
                val datai = tableGoals.getAllLongtime()
                val adapt = AdapterGoalsR(ArrayList(datai),view.context,this)
                binding.RcyclerLong.adapter = adapt
                binding.RcyclerLong.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
                alert.dismiss()

                binding.RcyclerLong.smoothScrollToPosition(0)
            }
        }
    }

    override fun clicked() {
        val mainActivity = activity as MainActivity
        val funclick = mainActivity.clicked()
    }
}