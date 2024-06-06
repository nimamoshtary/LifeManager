package ir.lifeplas.lifemanager.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.Adapters.AdapterTasksR
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.Tasksdao
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.DialogTasksBinding
import ir.lifeplas.lifemanager.databinding.FragmentTasksBinding
import ir.lifeplas.lifemanager.dataclass.TasksItem
import java.text.SimpleDateFormat
import java.util.Date
class TasksFragment : Fragment() , AdapterTasksR.Transferdata{
    lateinit var binding: FragmentTasksBinding
    lateinit var adapter : AdapterTasksR
    lateinit var tableTasks : Tasksdao
    companion object{
        var modelStart: Int = 0
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTasksBinding.inflate(inflater,container,false)
        return binding.root
    }
    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstrun(view)

        val SDF = SimpleDateFormat("yyyyMMdd")
        val currentDateandTime: String = SDF.format( Date() )

        if(TasksFragment.modelStart == 0){ binding.rdgSortby.check(R.id.t_rd_sota) }
        if(TasksFragment.modelStart == 1){ binding.rdgSortby.check(R.id.t_rd_fita) }
        if(TasksFragment.modelStart == 2){ binding.rdgSortby.check(R.id.t_rd_imup) }
        if(TasksFragment.modelStart == 3){ binding.rdgSortby.check(R.id.t_rd_urup) }

        binding.rdgSortby.setOnCheckedChangeListener { _ , checkedId ->
            when (checkedId) {
                R.id.t_rd_sota -> {
                    //runRecycler(0, view)
                    TasksFragment.modelStart = 0
                }
                R.id.t_rd_fita -> {
                    //runRecycler(1, view)
                    TasksFragment.modelStart = 1
                }
                R.id.t_rd_imup -> {
                    //runRecycler(2, view)
                    TasksFragment.modelStart = 2
                }
                R.id.t_rd_urup -> {
                    //runRecycler(3, view)
                    TasksFragment.modelStart = 3
                }
            }
        }
        //الان لیست تکست اینپوت لیوت رو پر کن
        binding.fab.setOnClickListener {
            val alert = AlertDialog.Builder(context).create()
            val dialogs = DialogTasksBinding.inflate(layoutInflater)
            alert.setView(dialogs.root)
            alert.setCancelable(true)
            alert.show()

            val goalsList = tableTasks.getTitleGoal()
            val adapt = ArrayAdapter( dialogs.root.context, R.layout.item_taskgoals, goalsList)
            (dialogs.autoComplete.editText as AutoCompleteTextView).setAdapter(adapt)

            dialogs.btnok.setOnClickListener {
                val txtname = dialogs.nameofaction.editText?.text.toString()
                val txtinfo = dialogs.infoofaction.editText?.text.toString()
                val goalSelected = dialogs.autoComplete.editText?.text.toString()
                val datebi = currentDateandTime

                val goalId = tableTasks.getGoalByTitle(goalSelected)
                val Goal = goalId[0].id
                val GoalImp = goalId[0].importance
                val GoalUrg = goalId[0].urgency

                val TaskA= TasksItem(
                    TextTitle = txtname,
                    TextSub = txtinfo,
                    DateBild = datebi.toInt(),
                    Goal = Goal!!,
                    GoalImp = GoalImp,
                    GoalUrg = GoalUrg,
                    CountActions = 0,
                    Status = false)
                tableTasks.insert(TaskA)
                runRecycler(view)
                alert.dismiss()
                binding.TRec.smoothScrollToPosition(0)
            }
        }
    }

    private fun firstrun(view: View) {
        tableTasks = database.getdb(view.context).tasksDao
        val data = tableTasks.getAllTasks()
        adapter = AdapterTasksR(ArrayList(data),view.context,this)
        binding.TRec.adapter = adapter
        binding.TRec.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)
    }
    fun runRecycler(view: View){
        val data = tableTasks.getbydatebild()
        adapter = AdapterTasksR(ArrayList(data),view.context,this)
        binding.TRec.adapter = adapter
        binding.TRec.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)
    }
    override fun clicked() {
    }
    override fun longcliked(TasksItem: TasksItem) {
    }
}