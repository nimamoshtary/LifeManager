package ir.lifeplas.lifemanager.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tableActions")
data class ActionsItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val textTitle: String,
    val textsub: String,
    val datebild: Int)