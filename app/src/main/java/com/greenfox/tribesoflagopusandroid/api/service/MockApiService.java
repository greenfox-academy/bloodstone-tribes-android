package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Location;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.User;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

/**
 * Created by User on 2017. 06. 20..
 */

public class MockApiService implements ApiService {

    private long id = 1;
    private String name = "kingdomname";
    private long idOfUser = 1;
    private Building farm1 = new Building(1, "farm", 1, 2);
    private Building mine1 = new Building(1, "mine", 1, 2);
    private Building townhall = new Building(1, "townhall", 1, 10);
    private List<Building> buildings = new ArrayList<>(Arrays.asList(farm1, mine1, townhall));
    private List<Resource> resources = new ArrayList<>();
    private List<Troop> troops = new ArrayList<>();
    private Location location = new Location(1, 1);
    private String type = "farm";
    private int level = 1;
    private int hp = 10;

    @Override
    public Call<BuildingsResponse> getBuildings(@Path("userId") int userId) {
        return new MockCall<BuildingsResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new BuildingsResponse(buildings)));
            }
        };
    }

    @Override
    public Call<Building> getCertainBuilding(@Path("userId") int userId, @Path("buildingId") int buildingId) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Building(1, type, level, hp)));
            }
        };
    }

    @Override
    public Call<Kingdom> getKingdom(@Path("userId") final int userId) {
        return new MockCall<Kingdom>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Kingdom(id, name, idOfUser, buildings, resources, troops, location)));
            }
        };
    }
}

