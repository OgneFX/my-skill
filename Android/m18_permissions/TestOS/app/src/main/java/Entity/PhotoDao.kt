package Entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhoto(photo: Photo)

    @Query("SELECT * FROM photo")
    fun getPhoto(): List<Photo>


}