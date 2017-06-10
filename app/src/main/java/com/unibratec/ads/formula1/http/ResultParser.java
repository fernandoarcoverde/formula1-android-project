package com.unibratec.ads.formula1.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.gson.Gson;
import com.unibratec.ads.formula1.model.DriverPosition;
import com.unibratec.ads.formula1.model.RaceDriverResult;
import com.unibratec.ads.formula1.model.RaceDriverSeries;
import com.unibratec.ads.formula1.model.SeasonResult;
import com.unibratec.ads.formula1.model.SeasonRound;
import com.unibratec.ads.formula1.model.SeasonSeries;
import com.unibratec.ads.formula1.model.WiikiPediaResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 08/06/17.
 */

public class ResultParser {

    public static final String URL_SEASON_LIST     = "http://ergast.com/api/f1/%s/driverStandings.json";
    public static final String URL_API_WIIKIPEDIA  = "https://en.wikipedia.org/w/api.php?action=query&titles=%1$s&prop=pageimages&formatversion=2&format=json&pithumbsize=%2$s";
    public static final String URL_RACES_DRIVER    = "http://ergast.com/api/f1/%1$s/drivers/%2$s/results.json";
    public static final String PICTURE_SIZE_SMALL  = "100";
    public static final String PICTURE_SIZE_MEDIUM = "600";

    private static OkHttpClient client = new OkHttpClient();



    public static SeasonSeries getSeasonList(Context context, String query) throws IOException {
        ConnectivityManager cm =(ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi   = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected() || mobile.isConnected()) {
            Request request = new Request.Builder().url(String
                    .format(URL_SEASON_LIST, query)).build();

            Response response = client.newCall(request).execute();
            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                String json = response.body().string();

                Gson gson = new Gson();

                SeasonResult result = gson.fromJson(json, SeasonResult.class);

                List<SeasonRound> seasonRounds = result.seasonSeries.seasonYear.seasonRounds;

                if (result != null && seasonRounds.size() > 0) {
                    result.seasonSeries.seasonYear.seasonRounds.get(0).driverStandings =
                            setURLImageForDrivers(seasonRounds.get(0).driverStandings,
                                    seasonRounds.get(0).season,
                                    seasonRounds.get(0).round);
                    return result.seasonSeries;
                }
            }
        }
        return null;
    }

    public static RaceDriverSeries getRaceDriverTable(Context context, Bundle bundle) throws IOException {

        ConnectivityManager cm =(ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi   = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected() || mobile.isConnected()) {
            Request request = new Request.Builder().url(String
                    .format(URL_RACES_DRIVER, bundle.get("seasonYear"), bundle.get("driverId"))).build();

            Response response = client.newCall(request).execute();
            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                String json = response.body().string();

                Gson gson = new Gson();

                RaceDriverResult result = gson.fromJson(json, RaceDriverResult.class);

                if (result != null) {
                    result.raceDriverSeries.raceDriverRace
                            .setSeasonYear(bundle.getString("seasonYear"));
                    result.raceDriverSeries.raceDriverRace
                            .setRound(bundle.getString("round"));
                    result.raceDriverSeries.raceDriverRace
                            .setPosition(bundle.getString("position"));
                    result.raceDriverSeries.raceDriverRace
                            .setWins(bundle.getString("wins"));
                    result.raceDriverSeries.raceDriverRace
                            .setPoints(bundle.getString("points"));
                    result.raceDriverSeries.raceDriverRace
                            .setUrlImageDriver(bundle.getString("urlImage"));
                    result.raceDriverSeries.raceDriverRace
                            .setUrlImagePosterDriver(setURLImageDriver(bundle.getString("driverGivenName") +
                                    " " + bundle.getString("driverFamilyName")));
                    result.raceDriverSeries.raceDriverRace
                            .getDriver().setDriverId(bundle.getString("driverId"));
                    result.raceDriverSeries.raceDriverRace
                            .getDriver().setGivenName(bundle.getString("driverGivenName"));
                    result.raceDriverSeries.raceDriverRace
                            .getDriver().setFamilyName(bundle.getString("driverFamilyName"));
                    result.raceDriverSeries.raceDriverRace
                            .getDriver().setNationality(bundle.getString("driverCountry"));
                    result.raceDriverSeries.raceDriverRace
                            .getDriver().setDateOfBirth(bundle.getString("dateBirth"));
                    result.raceDriverSeries.raceDriverRace
                            .getDriver().setCode(bundle.getString("code"));
                    result.raceDriverSeries.raceDriverRace
                            .getDriver().setPermanentNumber(bundle.getString("number"));
                    result.raceDriverSeries.raceDriverRace
                            .getConstructors().get(0).setName(bundle.getString("constructorName"));
                    result.raceDriverSeries.raceDriverRace
                            .getConstructors().get(0).setNationality(bundle.getString("constructorCountry"));

                    return result.raceDriverSeries;
                }
            }
        }
        return null;
    }


    private static List<DriverPosition> setURLImageForDrivers(
            List<DriverPosition> driverStandings, String year, String round) throws IOException {

        int totalDrivers = driverStandings.size();

        Request request;
        Response response;
        String json;
        Gson gson;
        WiikiPediaResult result;

        for (int i = 0; i < totalDrivers ; i++) {
            driverStandings.get(i).setSeasonYear(year);
            driverStandings.get(i).setRound(round);

            StringBuilder driverName = new StringBuilder(driverStandings.get(i).getDriver().getGivenName());
            driverName.append(" ");
            driverName.append(driverStandings.get(i).getDriver().getFamilyName());

            request = new Request.Builder().url(String.format(
                    URL_API_WIIKIPEDIA, driverName, PICTURE_SIZE_SMALL)).build();

            response = client.newCall(request).execute();

            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                json = response.body().string();
                gson = new Gson();
                result = gson.fromJson(json, WiikiPediaResult.class);
                if (result != null && result.query.pages.get(0).thumbnail != null) {
                    driverStandings.get(i).setUrlImageDriver(
                            result.query.pages.get(0).thumbnail.source);
                }
            }
        }
        return driverStandings;
    }

    private static String setURLImageDriver(String driverName) throws IOException {

        Request request = new Request.Builder().url(String.format(
                URL_API_WIIKIPEDIA, driverName, PICTURE_SIZE_MEDIUM)).build();

        Response response = client.newCall(request).execute();

        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();
            Gson gson = new Gson();
            WiikiPediaResult result = gson.fromJson(json, WiikiPediaResult.class);
            if (result != null && result.query.pages.get(0).thumbnail != null) {
                return result.query.pages.get(0).thumbnail.source;
            }
        }

        return null;
    }



}