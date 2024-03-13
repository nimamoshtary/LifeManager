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
import ir.lifeplas.lifemanager.Goalsdao
import ir.lifeplas.lifemanager.MainActivity
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.DialogGoalsBinding
import ir.lifeplas.lifemanager.databinding.FragmentGoalsBinding
import ir.lifeplas.lifemanager.dataclass.GoalsItem
import java.text.SimpleDateFormat
import java.util.Date

class GoalsFragment : Fragment() , AdapterGoalsR.Transferdata {
    lateinit var tableGoals : Goalsdao
    lateinit var adapter : AdapterGoalsR
    lateinit var binding: FragmentGoalsBinding
    companion object{
        var modelstart: Int = 0
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGoalsBinding.inflate(inflater,container,false)
        return binding.root
    }
    @SuppressLint("SimpleDateFormat", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstrun(view)

        val sdf = SimpleDateFormat("yyyyMMdd")
        val currentDateandTime: String = sdf.format(Date())

        if(modelstart == 0){ binding.radioGroup.check(R.id.radio_model0) }
        if(modelstart == 1){ binding.radioGroup.check(R.id.radio_model1) }
        if(modelstart == 2){ binding.radioGroup.check(R.id.radio_model2) }

        binding.radioGroup.setOnCheckedChangeListener { _ , checkedId ->
            when (checkedId) {
                R.id.radio_model0 -> {
                    runRecycler(0, view)
                    modelstart = 0
                }
                R.id.radio_model1 -> {
                    runRecycler(1, view)
                    modelstart = 1
                }
                R.id.radio_model2 -> {
                    runRecycler(2, view)
                    modelstart = 2
                }
            }
        }
        binding.fab.setOnClickListener {
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
                    model = modelstart,
                    importance = imp,
                    urgency = urg,
                    acions = 0,
                    fillaction = 0,
                    notfillaction = 0,
                    completion = 0)
                tableGoals.insert(Actionha)
                runRecycler(modelstart,view)
                alert.dismiss()
                binding.RcyclerGoals.smoothScrollToPosition(0)
            }
        }
    }
    fun firstrun(view: View){
        tableGoals = database.getdb(view.context).goalsDao
        val data = tableGoals.getbymodel(modelstart)
        adapter = AdapterGoalsR(ArrayList(data),view.context,this@GoalsFragment)
        binding.RcyclerGoals.adapter = adapter
        binding.RcyclerGoals.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)
    }
    fun runRecycler(model:Int , view: View){
        val data = tableGoals.getbymodel(model)
        adapter = AdapterGoalsR(ArrayList(data),view.context,this@GoalsFragment)
        binding.RcyclerGoals.adapter = adapter
        binding.RcyclerGoals.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)
    }
    override fun clicked() {
        val mainActivity = activity as MainActivity
        mainActivity.clicked()
    }
    override fun longcliked(goalsItem: GoalsItem, model: Int) {
        val mainActivity = activity as MainActivity
        mainActivity.longcliked(goalsItem,model)
    }
}