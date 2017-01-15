package com.pabloserrano.guardianreader;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.pabloserrano.guardianreader.module.AppModule;
import com.pabloserrano.guardianreader.module.NetModule;
import com.pabloserrano.guardianreader.module.RepositoryModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class MyApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .repositoryModule(new RepositoryModule())
                .build();

        Timber.plant(new Timber.DebugTree());
    }

    public AppComponent getComponent() {
        return component;
    }

    @VisibleForTesting
    public void setComponent(AppComponent appComponent) {
        this.component = appComponent;
    }
}
