package id.pixis.dompetkita.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.pixis.dompetkita.data.database.RoomDB
import id.pixis.dompetkita.repository.DataRepository
import id.pixis.dompetkita.repository.LocalRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideLocalRepository(
        db : RoomDB,
        disposable: CompositeDisposable
    ) : LocalRepository = LocalRepository(db, disposable)

    @Provides
    @Singleton
    fun provideDataRepository(
        localRepository: LocalRepository
    ) : DataRepository = DataRepository(localRepository)

    @Singleton
    @Provides
    fun provideDisposible() : CompositeDisposable = CompositeDisposable()
}