package ir.lifeplas.lifemanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.lifeplas.lifemanager.dataclass.ActionsItem
import ir.lifeplas.lifemanager.dataclass.GoalsItem
import ir.lifeplas.lifemanager.dataclass.TasksItem

@Database(
    entities = [ActionsItem::class, GoalsItem::class, TasksItem::class],
    version = 10,
    exportSchema = false)
abstract class database :RoomDatabase(){
    abstract val  actionsDao: Actionsdao
    abstract val  goalsDao: Goalsdao
    abstract val  tasksDao: Tasksdao
    companion object{
        @Volatile
        private var databasee: database? = null
        fun getdb(context: Context): database{
            synchronized(this){
                var instance= databasee
                if(instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,database::class.java,"DB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}