package Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photo")
data class Photo(
    @PrimaryKey
    @ColumnInfo
    var urlPath: String,
    @ColumnInfo
    var data: String

)
