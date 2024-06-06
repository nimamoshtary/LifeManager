package ir.lifeplas.lifemanager.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.dataclass.TasksItem

class AdapterTasksR(private val data: ArrayList<TasksItem>, val context: Context, val trada: AdapterTasksR.Transferdata) : RecyclerView.Adapter<AdapterTasksR.Recycler>() {

    inner class Recycler(Item: View) : RecyclerView.ViewHolder(Item) {
        var txttitle = Item.findViewById<TextView>(R.id.txt_title)
        var txtsub = Item.findViewById<TextView>(R.id.txt_sub)
        var txtimp = Item.findViewById<TextView>(R.id.txt_imp)
        var txturg = Item.findViewById<TextView>(R.id.txt_urg)
        var imgmore = Item.findViewById<ImageView>(R.id.img_more)
        @SuppressLint("NotifyDataSetChanged")
        fun bindData(position: Int) {
            val cur = data[position]
            txttitle.text = cur.TextTitle
            txtsub.text = cur.TextSub
            //!!!!!!!!!!!! ببین ما به ضرورت و فوریت هدفش نیاز داریم پس با یه طریقی اینجا بیارش
            // تگ هاش رو هم فعلا نیاز نداریم ولی باید بگیریم
            // تعداد فعالیت های داخلش هم باید بگیریم
            // رنگ و عکس هدف هم باید بگیری
            //var impt = "ضروری"
            //var imps = 12f
            //val red = Color.RED
            //val orenge = Color.argb(255,255,152,0)
            //val yellow = Color.YELLOW
            //val white = Color.WHITE
            //var cimpt = red
//            when (cur.importance) {
//                4 -> {}
//                3 -> {
//                    impt = "نه چندان ضروری"
//                    cimpt = orenge
//                    imps = 10f
//                }
//                2 -> {
//                    impt = "عادی"
//                    cimpt = yellow
//                }
//                1 -> {
//                    impt = "غیر ضروری"
//                    cimpt = white
//                }
//            }
//            var urgt = "فوری"
//            var curg = orenge
//            var urgs = 12f
//            when (cur.urgency) {
//                3 -> { }
//                2 -> {
//                    urgt = "نه چندان فوری"
//                    curg = yellow
//                    urgs = 10f
//                }
//                1 -> {
//                    urgt = "عادی"
//                    curg = white
//                }
//            }
//            txtimp.text = impt
            //txtimp.background.setTint(cimpt)
//            txtimp.textSize = imps
            //txturg.text = urgt
            //txturg.background.setTint(curg)
            //txturg.textSize = urgs
            imgmore.setOnClickListener {
                val tableGoals = database.getdb(context).tasksDao
                tableGoals.delete(cur)
                data.remove(cur)
                notifyDataSetChanged()
            }
            itemView.setOnClickListener {
                trada.clicked()
            }
            itemView.setOnLongClickListener {
                //trada.longcliked(cur)
                true
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_actions,parent,false)
        return Recycler(layout)
    }
    override fun onBindViewHolder(holder: Recycler, position: Int) {
        holder.bindData(position)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    interface Transferdata {
        fun clicked()
        fun longcliked(TasksItem: TasksItem)
    }
}