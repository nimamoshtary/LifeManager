package ir.lifeplas.lifemanager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.lifeplas.lifemanager.dataclass.ActionsItem

@Dao
interface Actionsdao {
    @Insert
    fun insert(Data:ActionsItem)
    @Query("SELECT * FROM tableActions")
    fun getall():List<ActionsItem>

}