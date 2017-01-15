package com.pabloserrano.guardianreader.module;

import com.pabloserrano.guardianreader.api.ApiService;
import com.pabloserrano.guardianreader.data.GuardianNewsRepositoryImpl;
import com.pabloserrano.guardianreader.data.local.GuardianNewsLocalDataSource;
import com.pabloserrano.guardianreader.data.remote.GuardianNewsRemoteDataSource;
import com.pabloserrano.guardianreader.main.MainPresenterImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public GuardianNewsRepositoryImpl provideItemRepository(ApiService apiService) {
        return new GuardianNewsRepositoryImpl(new GuardianNewsLocalDataSource(), new GuardianNewsRemoteDataSource(apiService));
    }

    @Singleton
    @Provides
    public MainPresenterImp provideMainPresenterHeroes(GuardianNewsRepositoryImpl guardianNewsRepository) {
        return new MainPresenterImp(guardianNewsRepository);
    }
}
