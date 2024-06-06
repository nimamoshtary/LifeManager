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

@Entity("tableGoals")
data class GoalsItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    var textTitle: String,
    val textsub: String,
    val datebild: Int,
    val datefill: Int?=null,
    val model: Int,
    val importance: Int,
    val urgency: Int,
    val acions: Int,
    val fillaction: Int,
    val notfillaction: Int,
    val completion: Int,
    val timespent: Int?=null)

@Entity("tableTasks")
data class TasksItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val TextTitle: String,
    val TextSub: String,
    val Goal: Int, //id Goal
    val GoalImp : Int,
    val GoalUrg : Int,
    val DateBild: Int,
    val Tags: String?=null,
    val CountActions : Int,
    val Status : Boolean /* true for copmleted */ )

