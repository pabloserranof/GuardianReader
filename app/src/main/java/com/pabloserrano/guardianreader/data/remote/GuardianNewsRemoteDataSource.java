package com.pabloserrano.guardianreader.data.remote;

import com.pabloserrano.guardianreader.Constants;
import com.pabloserrano.guardianreader.api.ApiService;
import com.pabloserrano.guardianreader.data.GuardianNewsDataSource;
import com.pabloserrano.guardianreader.data.model.GuardianSearch;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuardianNewsRemoteDataSource implements GuardianNewsDataSource {

    @Inject
    ApiService apiService;

    public GuardianNewsRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getNewsBySearchKey(final GetNewsCallback getNewsCallback, int startingPage) {

        apiService.getNewsSearch(startingPage, Constants.SEARCH_KEY, Constants.PAGE_SIZE, Constants.FIELDS, ApiService.API_KEY).enqueue(new Callback<GuardianSearch>() {
            @Override
            public void onResponse(Call<GuardianSearch> call, Response<GuardianSearch> response) {
                if (response.isSuccessful()) {
                    getNewsCallback.onNewsLoaded(response.body());
                } else {
                    getNewsCallback.onNewsNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<GuardianSearch> call, Throwable t) {
                getNewsCallback.onNewsNotAvailable();
            }
        });
    }
}
