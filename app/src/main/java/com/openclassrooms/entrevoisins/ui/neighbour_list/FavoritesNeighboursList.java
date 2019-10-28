package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavNeighbourEvents;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoritesNeighboursList extends Fragment {
    private List<Neighbour> mFavorites;
    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    public static FavoritesListRecyclerviewAdapter mAdapter;


  public static FavoritesNeighboursList newInstance(){
     FavoritesNeighboursList fragment = new FavoritesNeighboursList();
     return fragment;
  }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService= DI.getNeighbourApiService();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragement_favorites_list, container, false);
        Context context = view.getContext();
         mRecyclerView = (RecyclerView)view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }

   @Override
    public void onResume() {
       initList();
       super.onResume();}

    private void initList() {

            mFavorites = mApiService.getFavoritesNeighbour();
            mAdapter= new FavoritesListRecyclerviewAdapter(mFavorites);
            mRecyclerView.setAdapter(mAdapter);
      }



    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }



    @Subscribe
    public void onDeleteFavNeighbour(DeleteFavNeighbourEvents event) {
        mApiService.deleteFavorites(event.neighbour);
        initList();
    }

}
