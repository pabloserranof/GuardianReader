package com.pabloserrano.guardianreader.data;

import com.pabloserrano.guardianreader.data.model.GuardianSearch;

public interface GuardianNewsDataSource {

    interface GetNewsCallback {
        void onNewsLoaded(GuardianSearch guardianSearch);
        void onNewsNotAvailable();
    }

    void getNewsBySearchKey(GuardianNewsDataSource.GetNewsCallback newsCallback, int startingPage);
}
