package ir.lifeplas.lifemanager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.lifeplas.lifemanager.dataclass.ActionsItem
import ir.lifeplas.lifemanager.dataclass.GoalsItem

interface BaseDao<T> {

    @Insert
    fun insert(obj: T)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}

@Dao
interface Actionsdao : BaseDao<ActionsItem> {

    @Query("SELECT * from tableActions ")
    fun getAllActions(): List<ActionsItem>

}

@Dao
interface Goalsdao : BaseDao<GoalsItem> {
    @Query("SELECT * from tableGoals ")
    fun getAllGoals(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=1 ORDER BY importance DESC")
    fun getAllShortterm(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=2")
    fun getAllMidterm(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=3")
    fun getAllLongtime(): List<GoalsItem>
}

//@Dao
//interface Actionsdao {
//    @Insert
//    fun insert(Data:ActionsItem)
//    @Query("SELECT * FROM tableActions")
//    fun getall():List<ActionsItem>
//}
//@Dao
//interface Goalsdao {
//    @Insert
//    fun insert(Data: GoalsItem)
//    @Query("SELECT * FROM tableGoals")
//    fun getall():List<GoalsItem>
//}