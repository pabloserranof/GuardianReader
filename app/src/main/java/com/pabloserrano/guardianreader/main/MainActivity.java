package com.pabloserrano.guardianreader.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pabloserrano.guardianreader.Constants;
import com.pabloserrano.guardianreader.MyApplication;
import com.pabloserrano.guardianreader.R;
import com.pabloserrano.guardianreader.data.model.GuardianSearch;
import com.pabloserrano.guardianreader.data.model.Result;
import com.pabloserrano.guardianreader.main.adapter.GuardianNewsAdapter;
import com.pabloserrano.guardianreader.newsdetails.NewsDetailsActivity;
import com.pabloserrano.guardianreader.baseclasses.BaseActivity;
import com.pabloserrano.guardianreader.utils.NetworkUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainPresenterImp.View {

    @Inject
    MainPresenterImp presenter;

    @BindView(R.id.recycler_view_city_list)
    RecyclerView recyclerView;

    private GuardianNewsAdapter adapter;
    private boolean isFetchingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();
        presenter.initialize();
    }

    @Override
    public void showGuardianSearchList(GuardianSearch guardianSearches) {
        adapter.addAll(guardianSearches.getResponse().getResults());
        adapter.notifyDataSetChanged();
        isFetchingData = false;
    }

    @Override
    public void showNotAvailable() {
        Snackbar.make(findViewById(android.R.id.content), R.string.generic_error, Snackbar.LENGTH_LONG).setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.initialize();
            }
        }).show();
    }

    @Override
    public void openNewScreen(Result news) {
        if (NetworkUtils.isNetworkAvailable(this)) {
            NewsDetailsActivity.open(this, news.getWebUrl());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initializeDagger() {
        ((MyApplication) getApplication()).getComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void initializeAdapter() {
        adapter = new GuardianNewsAdapter(presenter);
    }

    private void initializeRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (!isFetchingData) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= Constants.PAGE_SIZE) {
                        presenter.getNewsFromPage(totalItemCount);
                        isFetchingData = true;
                    }
                }
            }
        });
    }
}
