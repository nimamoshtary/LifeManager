package ir.lifeplas.lifemanager.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.database
import ir.lifeplas.lifemanager.dataclass.GoalsItem

class AdapterGoalsR(private val data:ArrayList<GoalsItem>,val context:Context) : RecyclerView.Adapter<AdapterGoalsR.Recycler>() {
    inner class Recycler(Item :View) :RecyclerView.ViewHolder(Item){
        var txttitle = Item.findViewById<TextView>(R.id.txt_title)
        var txtsub = Item.findViewById<TextView>(R.id.txt_sub)
        var txtimp = Item.findViewById<TextView>(R.id.txt_imp)
        var txturg = Item.findViewById<TextView>(R.id.txt_urg)
        var imgmore = Item.findViewById<ImageView>(R.id.img_more)
        var imgfil = Item.findViewById<ImageView>(R.id.img_complet)

        fun bindData(position:Int){
            txttitle.text = data[position].textTitle
            txtsub.text = data[position].textsub
            var impt = "ضروری"
            var imps = 12f
            val red = Color.RED
            val orenge = Color.argb(255,255,152,0)
            val yellow = Color.YELLOW
            val white = Color.WHITE
            var cimpt = red
            when(data[position].importance){
                4 -> {
                    impt="ضروری"
                    cimpt=red
                }
                3 -> {impt="نه چندان ضروری"
                    cimpt=orenge
                    imps= 10f
                }
                2 -> {impt="عادی"
                    cimpt=yellow
                }
                1 -> {impt="غیر ضروری"
                    cimpt=white
                }
            }
            var urgt = "فوری"
            var curg = red
            when(data[position].urgency){
                3 -> {urgt="فوری"
                    curg=orenge
                }
                2 -> {urgt="نه چندان فوری"
                    curg=yellow
                }
                1 -> {urgt="عادی"
                    curg=white
                }
            }
            txtimp.text = impt
            txtimp.background.setTint(cimpt)
            txtimp.textSize = imps
            txturg.text = urgt
            txturg.background.setTint(curg)
            imgmore.setOnClickListener{
                val tableGoals = database.getdb(context).goalsDao
                val Actionha= GoalsItem(
                    id = data[position].id,
                    textTitle = data[position].textTitle,
                    textsub = data[position].textsub,
                    datebild = data[position].datebild,
                    model = data[position].model,
                    importance = data[position].importance,
                    urgency = data[position].urgency,
                    acions = data[position].acions,
                    fillaction = data[position].fillaction,
                    notfillaction = data[position].notfillaction,
                    completion = data[position].completion)
                tableGoals.delete(Actionha)
                data.remove(Actionha)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_goals,parent,false)
        return Recycler(layout)
    }

    override fun onBindViewHolder(holder: Recycler, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
