package di

import Data.UsefulActivitiesRepository
import Domain.GetUsefulActivityUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetUsefulActivityUseCase(UsefulActivityRepository:UsefulActivitiesRepository): GetUsefulActivityUseCase {
        return GetUsefulActivityUseCase(UsefulActivityRepository)
    }


}