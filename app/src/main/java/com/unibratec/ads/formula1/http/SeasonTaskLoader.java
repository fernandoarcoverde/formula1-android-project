package com.unibratec.ads.formula1.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.unibratec.ads.formula1.model.SeasonSeries;

import java.io.IOException;

/**
 * Created by root on 08/06/17.
 */

public class SeasonTaskLoader extends AsyncTaskLoader<SeasonSeries> {

    private SeasonSeries mSeasonSeries;
    private String mQuery;
    private Context mContext;


    public SeasonTaskLoader(Context context, String query) {
        super(context);
        mQuery = query;
        mContext = context;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mQuery == null) return;

        if (mSeasonSeries == null) {
            forceLoad();
        } else {
            deliverResult(mSeasonSeries);
        }

    }

    @Override
    public SeasonSeries loadInBackground() {
        try {
            mSeasonSeries = ResultParser.getSeasonList(mContext, mQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mSeasonSeries;
    }
}