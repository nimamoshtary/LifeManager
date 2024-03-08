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
    @Query("SELECT * FROM tableGoals WHERE model=:model ORDER BY importance + urgency DESC")
    fun getbymodel(model: Int):List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=2 ORDER BY importance + urgency DESC")
    fun getAllShortterm(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=1 ORDER BY importance + urgency DESC")
    fun getAllMidterm(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=0 ORDER BY importance + urgency DESC")
    fun getAllLongtime(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=2 ORDER BY urgency DESC")
    fun getAllUShortterm(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=2 ORDER BY importance DESC")
    fun getAllIShortterm(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=1 ORDER BY urgency DESC")
    fun getAllUMidterm(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=1 ORDER BY importance DESC")
    fun getAllIMidterm(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=0 ORDER BY urgency DESC")
    fun getAllULongtime(): List<GoalsItem>
    @Query("SELECT * FROM tableGoals WHERE model=0 ORDER BY importance DESC")
    fun getAllILongtime(): List<GoalsItem>
}