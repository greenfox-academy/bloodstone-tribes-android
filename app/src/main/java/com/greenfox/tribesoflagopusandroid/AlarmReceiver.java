package com.greenfox.tribesoflagopusandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by georgezsiga on 6/27/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Inject
    ApiService apiService;

    Kingdom thisKingdom = new Kingdom();

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        TribesApplication.app().basicComponent().inject(this);

        apiService.getKingdom(1).enqueue(new Callback<Kingdom>() {
            @Override
            public void onResponse(Call<Kingdom> call, Response<Kingdom> response) {
                thisKingdom = response.body();
            }

            @Override
            public void onFailure(Call<Kingdom> call, Throwable t) {

            }
        });
    }

}
