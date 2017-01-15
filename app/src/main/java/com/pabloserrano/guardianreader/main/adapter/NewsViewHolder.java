package com.pabloserrano.guardianreader.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pabloserrano.guardianreader.R;
import com.pabloserrano.guardianreader.data.model.Result;
import com.pabloserrano.guardianreader.main.MainPresenterImp;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private final MainPresenterImp presenter;

    @BindView(R.id.newsTitle)
    TextView newsTitle;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.thumbnail)
    ImageView thumbnailView;
    @BindView(R.id.sectionName) TextView sectionName;

    public NewsViewHolder(View itemView, MainPresenterImp presenter) {
        super(itemView);
        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(Result news) {
        hookListeners(news);
        renderNewsTitle(news.getWebTitle());
        renderNewsDate(news.getWebPublicationDate());
        renderNewsPhoto(news);
        renderNewsSectionName(news.getSectionName());
    }

    private void hookListeners(final Result result) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onNewsClicked(result);
            }
        });
    }

    private void renderNewsTitle(String title) {
        newsTitle.setText(title);
    }

    private void renderNewsSectionName(String section) {
        sectionName.setText(section);
    }

    private void renderNewsDate(String webPublicationDate) {
        date.setText(webPublicationDate.split("T")[0]);
    }

    private void renderNewsPhoto(Result news) {
        try {
            Picasso.with(getContext()).load(news.getFields().getThumbnail()).fit().centerCrop().into(thumbnailView);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            Timber.e(news.toString());
        }
    }

    private Context getContext() {
        return itemView.getContext();
    }
}

