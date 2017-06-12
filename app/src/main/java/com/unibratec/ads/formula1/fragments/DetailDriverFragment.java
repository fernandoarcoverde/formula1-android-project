package com.unibratec.ads.formula1.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;
import com.unibratec.ads.formula1.CustomDialog;
import com.unibratec.ads.formula1.GetImage;
import com.unibratec.ads.formula1.R;
import com.unibratec.ads.formula1.Translate;
import com.unibratec.ads.formula1.dao.DbEvent;
import com.unibratec.ads.formula1.dao.FavoritesDAO;
import com.unibratec.ads.formula1.http.RacesDriverTaskLoader;
import com.unibratec.ads.formula1.model.RaceDriverRace;
import com.unibratec.ads.formula1.model.RaceDriverSeries;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by root on 10/06/17.
 */

public class DetailDriverFragment extends Fragment implements LoaderManager.LoaderCallbacks<RaceDriverSeries>{

    public static final int MIN_VALUE_AXIS_X = 20;
    public static final int LEGEND_TEXT_SIZE = 30;
    public static final int LEGEND_PADING = 6;
    public static final int GRID_LABEL_TEXT_SIZE = 24;

    ProgressDialog mDialog;
    CustomDialog mCustomDialog;
    Translate translate;
    GetImage getImage;
    String mMessage_title_attention;
    String mMessage_body_error;
    String mMessage_body_download;
    RaceDriverRace raceDriverRace;
    FavoritesDAO favoritesDAO;
    FloatingActionButton fab;
    CollapsingToolbarLayout tBarLayout;

    ImageView imgDriver;
    TextView txtSeasonYear;
    TextView  txtRound;
    TextView  txtDriverCountry;
    TextView  txtDateBirth;
    TextView  txtTeam;
    TextView  txtTeamCountry;
    TextView  txtCode;
    TextView  txtNumber;
    TextView  txtPosition;
    TextView  txtWins;
    TextView  txtPoints;
    ImageView imgDriverFlag;
    ImageView imgConstructorFlag;
    ImageView imgConstructorLogo;
    GraphView grpLineGraph;


    public DetailDriverFragment() {
    }


    public static DetailDriverFragment newInstance(Bundle bundle){
        DetailDriverFragment detailDriverFragment = new DetailDriverFragment();
        detailDriverFragment.setArguments(bundle);
        return detailDriverFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMessage_title_attention = getResources().getString(R.string.title_attention);
        mMessage_body_error      = getResources().getString(R.string.body_error_download_driver_detail);
        mMessage_body_download   = getResources().getString(R.string.body_download_driver_detail);

        View view = inflater.inflate(R.layout.fragment_detail_driver, container, false);

        if(getResources().getBoolean(R.bool.phone)){
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            tBarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        }

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrRemoveFavorites();

            }
        });





        imgDriver           = (ImageView) view.findViewById(R.id.detail_image_driver);
        txtSeasonYear       = (TextView)  view.findViewById(R.id.detail_season);
        txtRound            = (TextView)  view.findViewById(R.id.detail_round);
        txtDriverCountry    = (TextView)  view.findViewById(R.id.detail_driver_country);
        txtDateBirth        = (TextView)  view.findViewById(R.id.detail_date_birth);
        txtTeam             = (TextView)  view.findViewById(R.id.detail_constructor_name);
        txtTeamCountry      = (TextView)  view.findViewById(R.id.detail_constructor_country);
        txtCode             = (TextView)  view.findViewById(R.id.detail_code);
        txtNumber           = (TextView)  view.findViewById(R.id.detail_number);
        txtPosition         = (TextView)  view.findViewById(R.id.detail_position);
        txtWins             = (TextView)  view.findViewById(R.id.detail_wins);
        txtPoints           = (TextView)  view.findViewById(R.id.detail_points);
        imgDriverFlag       = (ImageView) view.findViewById(R.id.detail_driver_flag);
        imgConstructorFlag  = (ImageView) view.findViewById(R.id.detail_constructor_flag);
        imgConstructorLogo  = (ImageView) view.findViewById(R.id.detail_constructor_logo);
        grpLineGraph        = (GraphView) view.findViewById(R.id.detail_line_graph);


        favoritesDAO = FavoritesDAO.getInstance(getActivity().getApplication().getApplicationContext());
        translate = Translate.getInstance(getActivity().getApplication().getApplicationContext());
        getImage  = GetImage.getInstance (getActivity().getApplication().getApplicationContext());

        raceDriverRace = favoritesDAO.getDriverRace(getArguments().getString("driverId"));
        if (raceDriverRace == null){
            getLoaderManager().initLoader(1, getArguments(), this);

        } else {
            updateUI(raceDriverRace, true);

        }

        return view;
    }


    @Override
    public Loader<RaceDriverSeries> onCreateLoader(int id, Bundle args) {
        mDialog = ProgressDialog.show(getActivity(), mMessage_title_attention, mMessage_body_download);
        return new RacesDriverTaskLoader(getActivity(), args);
    }


    @Override
    public void onLoadFinished(Loader<RaceDriverSeries> loader, RaceDriverSeries data) {
        if (mDialog != null){
            mDialog.dismiss();
        }

        if (data != null) {
            raceDriverRace = data.raceDriverRace;
            updateUI(raceDriverRace, false);
        } else {
            mCustomDialog.simpleMessage(getActivity(), mMessage_title_attention
                    , mMessage_body_error);
        }

    }


    private void updateUI(RaceDriverRace raceDriverRace, boolean isFavotite) {
        if (getResources().getBoolean(R.bool.phone)) {
            tBarLayout.setTitle(raceDriverRace.getDriver().getGivenName() +
                    " " + raceDriverRace.getDriver().getFamilyName());
        }
        Picasso.with(getActivity()).load(raceDriverRace.getUrlImagePosterDriver()).into(imgDriver);
        txtSeasonYear     .setText(raceDriverRace.getSeasonYear());
        txtRound          .setText(raceDriverRace.getRound());
        txtDriverCountry  .setText(getResources().getString(translate.Country(raceDriverRace.getDriver().getNationality())));
        imgDriverFlag     .setImageResource(getImage.Flag(raceDriverRace.getDriver().getNationality().toLowerCase()));
        txtDateBirth      .setText(raceDriverRace.getDriver().getDateOfBirth());
        txtTeam           .setText(raceDriverRace.getConstructors().get(0).getName());
        txtTeamCountry    .setText(getResources().getString(translate.Country(raceDriverRace.getConstructors().get(0).getNationality())));
        imgConstructorFlag.setImageResource(getImage.Flag(raceDriverRace.getConstructors().get(0).getNationality().toLowerCase()));
        imgConstructorLogo.setImageResource(getImage.Constructor(raceDriverRace.getConstructors().get(0).getName()));
        txtCode           .setText(raceDriverRace.getDriver().getCode());
        txtNumber         .setText(raceDriverRace.getDriver().getPermanentNumber());
        txtPosition       .setText(raceDriverRace.getPosition());
        txtWins           .setText(raceDriverRace.getWins());
        txtPoints         .setText(raceDriverRace.getPoints());

        changeFloatingButton(isFavotite);

        if (raceDriverRace.getRaceDriverRounds() != null) {
            int totalRaces = raceDriverRace.getRaceDriverRounds().size();

            int minValueAxisX = MIN_VALUE_AXIS_X;
            double value;

            DataPoint[] dataSerieGrid = new DataPoint[totalRaces];
            DataPoint[] dataFinalPosition = new DataPoint[totalRaces];

            for (int i = 0; i < totalRaces; i++) {

                value = Double.parseDouble(raceDriverRace.getRaceDriverRounds().get(i)
                        .raceDriverDetails.get(0).gridPosition);
                dataSerieGrid[i] = new DataPoint(i + 1, value);

                value = Double.parseDouble(raceDriverRace.getRaceDriverRounds().get(i)
                        .raceDriverDetails.get(0).finalRacePosition);
                dataFinalPosition[i] = new DataPoint(i + 1, value);
                minValueAxisX = value > MIN_VALUE_AXIS_X ? (int) value + 1 : minValueAxisX;

            }

            LineGraphSeries<DataPoint> serieGridPosition = new LineGraphSeries<>(dataSerieGrid);
            LineGraphSeries<DataPoint> serieFinalPosition = new LineGraphSeries<>(dataFinalPosition);

            serieGridPosition.setColor(getResources().getColor(R.color.colorGraphLineGreen));
            serieGridPosition.setTitle(getResources().getString(R.string.line_graph_series_grid));
            serieGridPosition.setDrawDataPoints(true);

            serieFinalPosition.setColor(getResources().getColor(R.color.colorGraphLineBlue));
            serieFinalPosition.setTitle(getResources().getString(R.string.line_graph_series_race));
            serieFinalPosition.setDrawDataPoints(true);

            grpLineGraph.setTitle(getResources().getString(R.string.line_graph_title));
            grpLineGraph.getGridLabelRenderer().setGridColor(Color.GRAY);
            grpLineGraph.getGridLabelRenderer().setTextSize(GRID_LABEL_TEXT_SIZE);
            grpLineGraph.getGridLabelRenderer().setNumHorizontalLabels(minValueAxisX);
            grpLineGraph.getViewport().setXAxisBoundsManual(true);
            grpLineGraph.getViewport().setMaxX(minValueAxisX);
            grpLineGraph.getLegendRenderer().setVisible(true);
            grpLineGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            grpLineGraph.getLegendRenderer().setPadding(LEGEND_PADING);
            grpLineGraph.getLegendRenderer().setTextSize(LEGEND_TEXT_SIZE);
            grpLineGraph.getLegendRenderer().setBackgroundColor(getResources().getColor(R.color.colorGraphBackgroundLegend));
            grpLineGraph.addSeries(serieGridPosition);
            grpLineGraph.addSeries(serieFinalPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<RaceDriverSeries> loader) {
    }


    public void saveOrRemoveFavorites(){
        RaceDriverRace rDR = favoritesDAO.getDriverRace(raceDriverRace.getDriver().getDriverId());
        if(rDR != null){
            favoritesDAO.deleteDriverRace(raceDriverRace);
            changeFloatingButton(false);

        } else {
            favoritesDAO.insertDriverRace(raceDriverRace);
            changeFloatingButton(true);
        }
        EventBus.getDefault().post(new DbEvent());
    }


    public void changeFloatingButton(boolean isFavorite){
        int resource = isFavorite ? R.drawable.ic_favorite_write : R.drawable.ic_favorite_black;
        fab.setImageResource(resource);
    }






}