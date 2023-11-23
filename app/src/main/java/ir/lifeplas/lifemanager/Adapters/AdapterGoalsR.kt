package ir.lifeplas.lifemanager.Adapters

import android.content.Context
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
            txttitle.text = data[position].textTitle.toString()
            txtsub.text = data[position].textsub.toString()
            var impt = "ضروری"
            when(data[position].importance){
                4 -> {impt="ضروری"}
                3 -> {impt="نه چندان ضروری"}
                2 -> {impt="عادی"}
                1 -> {impt="غیر ضروری"}
            }
            var urgt = "فوری"
            when(data[position].urgency){
                3 -> {urgt="فوری"}
                2 -> {urgt="نه چندان فوری"}
                1 -> {urgt="عادی"}
            }
            txtimp.text = impt.toString()
            txturg.text = urgt.toString()
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
