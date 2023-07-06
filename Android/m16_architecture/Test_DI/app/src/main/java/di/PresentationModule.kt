package di

import Domain.GetUsefulActivityUseCase
import Presentation.MainViewModel
import Presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class PresentationModule {

    @Provides
    fun provideMainViewModel(GetUsefulActivityUseCase: GetUsefulActivityUseCase): MainViewModel {
        return MainViewModel(GetUsefulActivityUseCase)
    }

    @Provides
    fun provideMainViewModelFactory(mainViewModel: MainViewModel): MainViewModelFactory {
            return MainViewModelFactory(mainViewModel)
    }

}