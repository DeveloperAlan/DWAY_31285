package com.mad.dway.view.activities;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.mad.dway.model.location.CurrentLocationRepository;
import com.mad.dway.model.places.Place;
import com.mad.dway.presenter.MainPresenter;
import com.mad.dway.view.fragments.JourneyDirectionsFragment;
import com.mad.dway.view.fragments.JourneyFragment;
import com.mad.dway.view.fragments.MapFragment;
import com.mad.dway.R;
import com.mad.dway.view.fragments.SearchResultsFragment;

/**
 * Activity of which the app starts off, connects all the activities and fragments together.
 * Has map fragment and journey fragment on launch
 *
 * @author  12934713
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        SearchResultsFragment.OnListFragmentInteractionListener,
        JourneyDirectionsFragment.OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private FirebaseAuth mAuth;
    private MapFragment mMapFragment;
    private MainPresenter mMainPresenter;
    private JourneyFragment mJourneyFragment;
    private CurrentLocationRepository mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Firebase authentication instance
        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mSectionsPagerAdapter.setPrimaryItem(mViewPager, 1, mMapFragment);

        mMainPresenter = new MainPresenter(this);
        mCurrentLocation = CurrentLocationRepository.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        mMainPresenter.checkUserInstance();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * When the user clicks on a list item on a Searched places list
     *
     * @return void
     */
    @Override
    public void onListFragmentInteraction(Place place) {
        double latitude = place.getLatitude();
        double longitude = place.getLongitude();
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragTransaction.replace(
                R.id.fragment_journey_root,
                JourneyDirectionsFragment.newInstance(place, mCurrentLocation.getLocation()),
                "fragment_journey_directions");
        fragTransaction.addToBackStack("journey_directions");
        fragTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(String direction) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment = null;

            if (position == 0) {
                fragment = mMapFragment.newInstance();
                mViewPager.setTag(R.integer.page2, fragment);
            } else if (position == 1) {
                fragment = mJourneyFragment.newInstance();
                mViewPager.setTag(R.integer.page3, fragment);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }
}
