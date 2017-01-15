package com.pabloserrano.guardianreader;

import com.pabloserrano.guardianreader.data.GuardianNewsDataSource;
import com.pabloserrano.guardianreader.data.GuardianNewsRepositoryImpl;
import com.pabloserrano.guardianreader.data.model.GuardianSearch;
import com.pabloserrano.guardianreader.main.MainPresenterImp;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class MainPresenterTests {

    @Mock
    GuardianNewsRepositoryImpl repository;

    @Mock
    MainPresenterImp.View mockView;

    MainPresenterImp presenter;

    @Mock
    GuardianNewsDataSource.GetNewsCallback getForecastCallback;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<GuardianNewsDataSource.GetNewsCallback> getNewsCallback;

    @Spy
    private GuardianSearch guardianSearch = new GuardianSearch();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenterImp(repository);
    }

    // TODO
    /*@Test
    public void shouldShowErrorMessageOnViewWhenCityListNotAvailableAndNotCallLoadCitys() {
        presenter.setView(mockView);
        setNewsNotAvailable(repository);
        verify(mockView, times(1)).showNotAvailable();
        verify(mockView, never()).showGuardianSearchList(guardianSearch);
    }

    // TODO
    @Test
    public void shouldNotShowErrorMessageOnViewWhenCityListAvailableAndCallLoadCity() {
        presenter.setView(mockView);
        setNewsAvailable(repository);
        verify(mockView, never()).showNotAvailable();
        verify(mockView, times(1)).showGuardianSearchList(guardianSearch);
    }*/

    private void setNewsNotAvailable(GuardianNewsDataSource forecastRepository) {
        verify(forecastRepository).getNewsBySearchKey(getNewsCallback.capture(), eq(1));
        getNewsCallback.getValue().onNewsNotAvailable();
    }

    private void setNewsAvailable(GuardianNewsDataSource forecastRepository) {
        verify(forecastRepository).getNewsBySearchKey(getNewsCallback.capture(), eq(1));
        getNewsCallback.getValue().onNewsLoaded(guardianSearch);
    }
}