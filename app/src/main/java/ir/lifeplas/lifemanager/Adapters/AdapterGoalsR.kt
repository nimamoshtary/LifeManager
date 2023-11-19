package ir.lifeplas.lifemanager.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.dataclass.GoalsItem

class AdapterGoalsR(private val data:ArrayList<GoalsItem>) : RecyclerView.Adapter<AdapterGoalsR.Recycler>() {
    inner class Recycler(Item :View) :RecyclerView.ViewHolder(Item){
        var txttitle = Item.findViewById<TextView>(R.id.txt_title)
        var txtsub = Item.findViewById<TextView>(R.id.txt_sub)
        var imgmore = Item.findViewById<ImageView>(R.id.img_more)
        var imgfil = Item.findViewById<ImageView>(R.id.img_complet)

        fun bindData(position:Int){
            txttitle.text = data[position].textTitle.toString()
            txtsub.text = data[position].textsub.toString()
//            imgmore.setOnClickListener{
//                //Toast.makeText(this@AdapterActionsR,"بزودی....",Toast.LENGTH_LONG).show()
//            }
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

    fun addAction(action:GoalsItem){
        data.add( 0 , action )
        notifyItemInserted(0)
    }

}
