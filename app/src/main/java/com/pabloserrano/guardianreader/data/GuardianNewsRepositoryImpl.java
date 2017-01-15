package com.pabloserrano.guardianreader.data;

import android.support.annotation.NonNull;

public class GuardianNewsRepositoryImpl implements GuardianNewsDataSource {

    private GuardianNewsDataSource guardianNewsLocalDataSource;
    private GuardianNewsDataSource guardianNewsRemoteDataSource;

    public GuardianNewsRepositoryImpl(GuardianNewsDataSource guardianNewsLocalDataSource,
                                      GuardianNewsDataSource guardianNewsRemoteDataSource) {
        this.guardianNewsLocalDataSource = guardianNewsLocalDataSource;
        this.guardianNewsRemoteDataSource = guardianNewsRemoteDataSource;
    }

    @Override
    public void getNewsBySearchKey(@NonNull GetNewsCallback getCityListCallback, int startingPage) {
        // Only reading from the remote datasource for now
        guardianNewsRemoteDataSource.getNewsBySearchKey(getCityListCallback, startingPage);
    }
}