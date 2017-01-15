package com.pabloserrano.guardianreader.module;

import android.app.Application;

import com.pabloserrano.guardianreader.api.ApiService;
import com.pabloserrano.guardianreader.utils.NetworkUtils;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private static final int CACHE_SIZE = 10 * 1024 * 1024; // 10MB

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(final Cache cache, final Application application) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(interceptor);
        client.cache(cache).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                // Read from the cache when there is no network
                if (NetworkUtils.isNetworkAvailable(application)) {
                    request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                } else {
                    request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                }
                return chain.proceed(request);
            }
        });
        return client.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRestAdapter(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}