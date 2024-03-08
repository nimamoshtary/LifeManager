package ir.lifeplas.lifemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ir.lifeplas.lifemanager.MainActivity
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.SharedViewModel
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.databinding.FragmentEditGoalsBinding
import ir.lifeplas.lifemanager.dataclass.GoalsItem


class EditGoalsFragment() : Fragment(){
    lateinit var binding : FragmentEditGoalsBinding
    val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEditGoalsBinding.inflate(layoutInflater, null, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner) { newText ->
            binding.edtEdittitle.editText!!.setText(newText)
        }
        viewModel.textsub.observe(viewLifecycleOwner) { newText ->
            binding.edtEditsubtitle.editText!!.setText(newText)
        }
        val views = viewModel.view
        var impc = views.value!!.importance
        var urgc = views.value!!.urgency
        viewModel.view.observe(viewLifecycleOwner){ newText->
            when(newText.importance){
                4 -> { binding.radioImp.check(R.id.radio_imp4) }
                3 -> {
                    binding.radioImp.check(R.id.radio_imp3)
                    //impc = 3
                }
                2 -> {
                    binding.radioImp.check(R.id.radio_imp2)
                    //impc = 2
                }
                1 -> {
                    binding.radioImp.check(R.id.radio_imp1)
                    //impc = 1
                }
            }
            when(newText.urgency){
                3 -> {
                    binding.radioUrg.check(R.id.radio_urg3)
                }
                2 -> {
                    binding.radioUrg.check(R.id.radio_urg2)
                }
                1 -> {
                    binding.radioUrg.check(R.id.radio_urg1)
                }
            }
        }
        //finish receive and set data for the views
        binding.radioImp.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_imp4 -> { impc = 4 }
                R.id.radio_imp3 -> { impc = 3 }
                R.id.radio_imp2 -> { impc = 2 }
                R.id.radio_imp1 -> { impc = 1 }
            }
        }
        binding.radioUrg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_urg3 -> { urgc = 3 }
                R.id.radio_urg2 -> { urgc = 2 }
                R.id.radio_urg1 -> { urgc = 1 }
            }
        }

        binding.btnSabt.setOnClickListener {
            val txttitle = binding.edtEdittitle.editText?.text
            val txtSub = binding.edtEditsubtitle.editText?.text
            val GoalEx = GoalsItem(
                id = views.value!!.id,
                textTitle = txttitle.toString(),
                textsub = txtSub.toString(),
                datebild = views.value!!.datebild,
                model = views.value!!.model,
                importance = impc,
                urgency = urgc,
                acions = views.value!!.acions,
                fillaction = views.value!!.fillaction,
                notfillaction = views.value!!.notfillaction,
                completion = views.value!!.completion)
            val tableGoals = database.getdb(view.context).goalsDao
            tableGoals.update(GoalEx)
            //finish edit data for the database
            val posit = viewModel.position.value!!.toInt()
            val mainActivity = activity as MainActivity
            mainActivity.closingdrawer(posit,views.value!!.model)
            //close drawer and edit data for the adapter with reset Recyclerview
        }
    }
}
