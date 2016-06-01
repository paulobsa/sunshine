package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends ActionBarActivity {

    private ActionProvider mShareActionProvider;

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

        /**
        TODO
        Tirar o shareActionProvider da biblioteca de compatibilidade e colocar a nativa
        Locate MenuItem with ShareActionProvider
        **/

        MenuItem item = menu.findItem(R.id.item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = MenuItemCompat.getActionProvider(item);


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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            TextView detail = (TextView)rootView.findViewById(R.id.detail_text_view);

            Intent intent = getActivity().getIntent();
            detail.setText(intent.getStringExtra(Intent.EXTRA_TEXT));

            return rootView;
        }
    }
}
