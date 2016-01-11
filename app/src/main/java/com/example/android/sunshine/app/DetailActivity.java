package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends ActionBarActivity {
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


//        Intent intent = getIntent();
//        String forecast = intent.getStringExtra(Intent.EXTRA_TEXT);
//        Bundle bundle = new Bundle();
//        bundle.putString("forecast", forecast);
//
//        PlaceholderFragment fragment = new PlaceholderFragment();
//        fragment.setArguments(bundle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);

        MenuItem item = menu.findItem(R.id.item_share);
        mShareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(item);

        /*
        Work-around para pegar o valor do fragmento
        Realizar refatoração
         */
        FragmentManager fm = getSupportFragmentManager();
        fm.findFragmentById(R.id.fragment_detail);
        List<Fragment> fragments = fm.getFragments();
        PlaceholderFragment myFragment = (PlaceholderFragment) fragments.get(0);

        doShare(myFragment.mShareIntent);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplication(), "Settings", Toast.LENGTH_SHORT).show();
            Intent settingsActivity = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(settingsActivity);

        }

        return super.onOptionsItemSelected(item);
    }

    public void doShare(Intent shareIntent){
        // Connect the dots: give the ShareActionProvider its Share Intent
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private Intent mShareIntent;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            TextView detail = (TextView)rootView.findViewById(R.id.detail_text_view);

            Intent intent = getActivity().getIntent();
            detail.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
            String forecast = detail.getText().toString();
            createShareIntent(forecast);


            return rootView;
        }

        private void createShareIntent(String detail) {
            //Create shareIntent
            detail = detail.concat(" #SunshineApp");
            mShareIntent = new Intent();
            mShareIntent.setAction(Intent.ACTION_SEND);
            mShareIntent.setType("text/plain");
            mShareIntent.putExtra(Intent.EXTRA_TEXT, detail);
        }
    }
}
