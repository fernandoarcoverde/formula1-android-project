package com.unibratec.ads.formula1.http;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.unibratec.ads.formula1.model.RaceDriverSeries;

import java.io.IOException;

/**
 * Created by root on 10/06/17.
 */

public class RacesDriverTaskLoader extends AsyncTaskLoader<RaceDriverSeries> {

    private RaceDriverSeries mRaceDriverTable;
    private Context mContext;
    private Bundle mBundle;


    public RacesDriverTaskLoader(Context context, Bundle args) {
        super(context);
        mContext = context;
        mBundle = args;
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mBundle == null) return;

        if (mRaceDriverTable == null) {
            forceLoad();
        } else {
            deliverResult(mRaceDriverTable);
        }

    }


    @Override
    public RaceDriverSeries loadInBackground() {
        try {
            mRaceDriverTable = ResultParser.getRaceDriverTable(mContext, mBundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mRaceDriverTable;
    }
}