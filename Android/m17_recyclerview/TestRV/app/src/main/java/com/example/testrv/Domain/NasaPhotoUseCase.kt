package Domain

import Data.Photos
import Data.retrofit

class NasaPhotoUseCase {

    suspend fun getNasaPhoto(): List<Photos> {
        return retrofit.nasaPhoto().photos
    }

}