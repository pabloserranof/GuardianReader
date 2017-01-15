/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pabloserrano.guardianreader.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pabloserrano.guardianreader.R;
import com.pabloserrano.guardianreader.data.model.Result;
import com.pabloserrano.guardianreader.main.MainPresenterImp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GuardianNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final MainPresenterImp presenter;
  private final List<Result> newsList;

  public GuardianNewsAdapter(MainPresenterImp presenter) {
    this.presenter = presenter;
    this.newsList = new ArrayList<>();
  }

  public void addAll(Collection<Result> collection) {
    newsList.addAll(collection);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_news_list, parent, false);
    return new NewsViewHolder(view, presenter);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
    Result result = newsList.get(position);
    newsViewHolder.render(result);
  }

  @Override
  public int getItemCount() {
    return newsList.size();
  }
}
