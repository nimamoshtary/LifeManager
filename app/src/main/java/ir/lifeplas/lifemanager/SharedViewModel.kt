package ir.lifeplas.lifemanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.lifeplas.lifemanager.dataclass.GoalsItem

class SharedViewModel : ViewModel() {
    val text = MutableLiveData<String>()
    val textsub = MutableLiveData<String>()
    val imp = MutableLiveData<Int>()
    val view = MutableLiveData<GoalsItem>()
    var position = MutableLiveData<Int>()
    val fra = MutableLiveData<Int>()
}