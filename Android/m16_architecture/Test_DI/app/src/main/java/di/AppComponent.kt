package di

import Presentation.MainViewModelFactory
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {

    fun mainViewModelFactory(): MainViewModelFactory

}