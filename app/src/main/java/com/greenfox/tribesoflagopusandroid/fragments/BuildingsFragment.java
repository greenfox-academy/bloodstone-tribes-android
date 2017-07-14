package com.greenfox.tribesoflagopusandroid.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.greenfox.tribesoflagopusandroid.LFCB;
import com.greenfox.tribesoflagopusandroid.MainActivity;
import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.adapter.BuildingsAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BUILDINGS_FRAGMENT_SAVE;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USER_ACCESS_TOKEN;

public class BuildingsFragment extends BaseFragment {

    @Inject
    SharedPreferences preferences;
    @Inject
    ApiService apiService;

    SharedPreferences.Editor editor;
    String timestamp;
    private BuildingsAdapter buildingsAdapter;
    FloatingActionMenu buildingsFloatingMenu;
    FloatingActionButton addFarmFloatingButton, addMineFloatingButton, addBarrackFloatingButton;
    ListView listView;


    public BuildingsFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Buildings");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        View rootView = inflater.inflate(R.layout.fragment_buildings, container, false);
        buildingsAdapter = new BuildingsAdapter(getContext(), new ArrayList<Building>());
        listView = (ListView) rootView.findViewById(R.id.buildings_list);
        refreshActiveFragment(((MainActivity)getActivity()));

        buildingsFloatingMenu = (FloatingActionMenu) rootView.findViewById(R.id.add_building_menu);
        addFarmFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_farm_menu_item);
        addFarmFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), "farm").enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        apiService.addBuildingToList(response.body());
                        getBuildingsFromAPI(((MainActivity)getActivity()));
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {

                    }
                });
            }
        });

        addMineFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_mine_menu_item);
        addMineFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), "mine").enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        apiService.addBuildingToList(response.body());
                        refreshActiveFragment(((MainActivity)getActivity()));
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {

                    }
                });
            }
        });

        addBarrackFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_barrack_menu_item);
        addBarrackFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), "barrack").enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        apiService.addBuildingToList(response.body());
                        refreshActiveFragment(((MainActivity)getActivity()));
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {

                    }
                });
            }
        });

        return rootView;
    }

    public void getBuildingsFromAPI(final LFCB callback) {
        apiService.getBuildings(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                buildingsAdapter.clear();
                buildingsAdapter.addAll(response.body().getBuildings());
                listView.setAdapter(buildingsAdapter);
                if (callback != null) {
                    callback.loadingFinished();
                }
            }

            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void refreshActiveFragment(LFCB callback) {
        getBuildingsFromAPI(callback);
        super.refreshActiveFragment(callback);
    }

    @Override
    public void onStop() {
        super.saveOnExit(BUILDINGS_FRAGMENT_SAVE);
        timestamp = BaseFragment.timestamp;
        super.onStop();
    }

}
