package com.pabloserrano.guardianreader;

import com.pabloserrano.guardianreader.main.MainActivity;
import com.pabloserrano.guardianreader.module.AppModule;
import com.pabloserrano.guardianreader.module.NetModule;
import com.pabloserrano.guardianreader.module.RepositoryModule;
import com.pabloserrano.guardianreader.newsdetails.NewsDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivity target);
    void inject(NewsDetailsActivity target);
}
