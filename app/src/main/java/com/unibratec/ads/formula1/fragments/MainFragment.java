package com.unibratec.ads.formula1.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.unibratec.ads.formula1.CustomDialog;
import com.unibratec.ads.formula1.DriverPositionAdapter;
import com.unibratec.ads.formula1.OnDriverPositionClick;
import com.unibratec.ads.formula1.R;
import com.unibratec.ads.formula1.http.SeasonTaskLoader;
import com.unibratec.ads.formula1.model.DriverPosition;
import com.unibratec.ads.formula1.model.SeasonSeries;

import java.util.Calendar;

/**
 * Created by root on 08/06/17.
 */

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<SeasonSeries>,
        SearchView.OnQueryTextListener  {

    private String mMessageTitleAttention;
    private String mMessageBodyDownload;
    private String mMessageBodyError;

    private ListView mListDriverPositions;
    private LoaderManager mLoaderManager;
    private ProgressDialog mDialog;
    private CustomDialog mCustomDialog = new CustomDialog();


    public MainFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mMessageTitleAttention = getResources().getString(R.string.title_attention);
        mMessageBodyDownload   = getResources().getString(R.string.body_download_driver_standing);
        mMessageBodyError      = getResources().getString(R.string.body_error_download_driver_standing);

        mListDriverPositions = (ListView)view.findViewById(R.id.list_driver_positions);
        mListDriverPositions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if(getActivity() instanceof OnDriverPositionClick){
                    DriverPosition driverPosition = (DriverPosition) mListDriverPositions
                            .getItemAtPosition(position);

                    ((OnDriverPositionClick)getActivity()).onDriverPositionClick(driverPosition);

                }

            }
        });

        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(0, null, this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public Loader<SeasonSeries> onCreateLoader(int id, Bundle args) {
        mDialog = ProgressDialog.show(getActivity(), mMessageTitleAttention, mMessageBodyDownload);
        String q = args != null ? args.getString("q") : getCurrentYear();
        return new SeasonTaskLoader(getActivity(), q);
    }

    @Override
    public void onLoadFinished(Loader<SeasonSeries> loader, SeasonSeries data) {
        if (mDialog != null){
            mDialog.dismiss();
        }
        if (data != null) {
            mListDriverPositions.setAdapter(new DriverPositionAdapter(getActivity()
                    , data.seasonYear.seasonRounds.get(0).driverStandings));

        } else {
            mCustomDialog.simpleMessage(getActivity(), mMessageTitleAttention, mMessageBodyError);
        }
    }

    @Override
    public void onLoaderReset(Loader<SeasonSeries> loader) {
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("q", query);
        mLoaderManager.restartLoader(0, bundle, this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private static String getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }




}
