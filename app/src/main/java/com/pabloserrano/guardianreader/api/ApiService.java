package com.pabloserrano.guardianreader.api;

import com.pabloserrano.guardianreader.data.model.GuardianSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String API_KEY = "8071255c-16f7-46fe-ae78-7733ab563c77";
    String ENDPOINT = "https://content.guardianapis.com/";

    @GET("search?")
    Call <GuardianSearch> getNewsSearch(
            @Query("page") int page,
            @Query("q") String searchKey,
            @Query("page-size") int pageSize,
            @Query("show-fields") String fields,
            @Query("api-key") String apikey);
}
