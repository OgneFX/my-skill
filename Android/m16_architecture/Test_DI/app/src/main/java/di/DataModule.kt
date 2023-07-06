package di

import Data.UsefulActivitiesRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideUsefulActivitiesRepository(): UsefulActivitiesRepository {
        return UsefulActivitiesRepository()
    }

}