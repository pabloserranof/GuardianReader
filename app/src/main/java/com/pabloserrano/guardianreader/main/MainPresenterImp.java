package com.pabloserrano.guardianreader.main;

import com.pabloserrano.guardianreader.data.GuardianNewsDataSource;
import com.pabloserrano.guardianreader.data.model.GuardianSearch;
import com.pabloserrano.guardianreader.data.model.Result;
import com.pabloserrano.guardianreader.baseclasses.Presenter;

import javax.inject.Inject;

public class MainPresenterImp extends Presenter<MainPresenterImp.View> {

    @Inject
    GuardianNewsDataSource guardianNewsRepository;

    public MainPresenterImp(GuardianNewsDataSource guardianNewsRepository) {
        this.guardianNewsRepository = guardianNewsRepository;
    }

    @Override
    public void initialize() {
        super.initialize();
        getNewsFromPage(1);
    }

    public void getNewsFromPage(final int startingPage) {
        guardianNewsRepository.getNewsBySearchKey(new GuardianNewsDataSource.GetNewsCallback() {
            View view = getView();
            @Override
            public void onNewsLoaded(GuardianSearch guardianSearch) {
                view.hideLoading();
                view.showGuardianSearchList(guardianSearch);
            }

            @Override
            public void onNewsNotAvailable() {
                view.hideLoading();
                view.showNotAvailable();
            }
        }, startingPage);
    }

    public void onNewsClicked(Result news) {
        getView().openNewScreen(news);
    }

    public interface View extends Presenter.View {

        void showNotAvailable();

        void showGuardianSearchList(GuardianSearch guardianSearches);

        void openNewScreen(Result news);
    }
}
