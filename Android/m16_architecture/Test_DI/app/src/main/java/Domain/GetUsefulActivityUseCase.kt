package Domain

import Data.UsefulActivitiesRepository
import Entity.UsefulActivity

class GetUsefulActivityUseCase(private val UsefulActivityRepository: UsefulActivitiesRepository) {


   suspend fun execut(): UsefulActivity {
        return UsefulActivityRepository.getUsefulActivity()
    }




}