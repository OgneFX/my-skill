package Data

import Entity.Photo
import android.content.Context

class LocalRepository (public val context: Context) {
    fun getPhoto(): List<Photo>?
    {
        return AppDatabase.invoke(context).photoDao()?.getPhoto()
    }
    fun addPhoto(photo: Photo)
    {
        AppDatabase.invoke(context).photoDao()?.addPhoto(photo)
    }
}