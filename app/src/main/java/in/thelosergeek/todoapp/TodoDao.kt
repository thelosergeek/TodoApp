package `in`.thelosergeek.todoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao{


    @Insert
    suspend fun insertTask(model: Model):Long

    @Query("Select * from Model where completed != 1")
    fun getTask(): LiveData<List<Model>>

    @Query("Update Model Set completed =1 where id =:uid")
    fun finishTask(uid:Long)

    @Query("Delete from Model where id=:uid")
    fun deleteTask(uid: Long)
}