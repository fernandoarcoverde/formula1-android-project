package com.unibratec.ads.formula1;

/*

Projeto para fins didáticos do curso de Análise e Desenvolvimento de Sistemas
Faculdade UNIBRATEC - Recife - PE

 */

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.unibratec.ads.formula1.fragments.DetailDriverFragment;
import com.unibratec.ads.formula1.fragments.FavoritesDriversFragment;
import com.unibratec.ads.formula1.fragments.MainFragment;
import com.unibratec.ads.formula1.model.DriverPosition;

public class MainActivity extends AppCompatActivity implements OnDriverPositionClick{

    Toolbar toolbar;
    MainFragment mainFragment;
    ViewPager viewPager;
    SelectorPageAdapter selectorPageAdapter;

    FavoritesDriversFragment favoritesDriversFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buildViewPage();
    }


    private void buildViewPage(){
        viewPager = (ViewPager) findViewById(R.id.viewPage);
        selectorPageAdapter = new SelectorPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(selectorPageAdapter);

        TabLayout tablayout = (TabLayout) findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onDriverPositionClick(DriverPosition driverPosition) {
        Bundle bundle = new Bundle();

        bundle.putString("seasonYear", driverPosition.getSeasonYear());
        bundle.putString("round", driverPosition.getRound());
        bundle.putString("position", driverPosition.getPosition());
        bundle.putString("points", driverPosition.getPoints());
        bundle.putString("wins", driverPosition.getWins());
        bundle.putString("driverId", driverPosition.getDriver().getDriverId());
        bundle.putString("number", driverPosition.getDriver().getPermanentNumber());
        bundle.putString("code", driverPosition.getDriver().getCode());
        bundle.putString("urlImage", driverPosition.getUrlImageDriver());
        bundle.putString("urlImagePoster", driverPosition.getUrlImagePosterDriver());
        bundle.putString("driverGivenName", driverPosition.getDriver().getGivenName());
        bundle.putString("driverFamilyName", driverPosition.getDriver().getFamilyName());
        bundle.putString("driverCountry", driverPosition.getDriver().getNationality());
        bundle.putString("dateBirth", driverPosition.getDriver().getDateOfBirth());
        bundle.putString("constructorName", driverPosition.getConstructors()
                .get(driverPosition.getConstructors().size() - 1).getName());
        bundle.putString("constructorCountry", driverPosition.getConstructors().
                get(driverPosition.getConstructors().size() - 1).getNationality());

        if (getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(this, DetailDriverActivity.class);
            it.putExtras(bundle);
            startActivity(it);

        } else {
            DetailDriverFragment detailDriverFragment = DetailDriverFragment.newInstance(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_driver_detail, detailDriverFragment, "detailDriver")
                    .commit();
        }

    }


    public class SelectorPageAdapter extends FragmentPagerAdapter {


        public SelectorPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if (mainFragment == null){
                        mainFragment = new MainFragment();
                    }
                    return mainFragment;
                default:
                    if (favoritesDriversFragment == null){
                        favoritesDriversFragment = new FavoritesDriversFragment();
                    }
                    return favoritesDriversFragment;




            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return getResources().getString(R.string.title_viewpage_list);
                default:
                    return getResources().getString(R.string.title_viewpage_favorite);
            }
        }


    }



}
