package com.java.app.mustardseed;

<<<<<<< HEAD
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
=======
import android.app.Activity;
>>>>>>> 831ce17d24b66bea47d31099c0d4fb7590ac6c3c
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

<<<<<<< HEAD
public class InsertSD extends ActionBarActivity {
=======
public class InsertSD extends Activity {
>>>>>>> 831ce17d24b66bea47d31099c0d4fb7590ac6c3c

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_sd);

<<<<<<< HEAD
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
=======
>>>>>>> 831ce17d24b66bea47d31099c0d4fb7590ac6c3c
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.insert_sd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the AudioHome/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

<<<<<<< HEAD
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_insert_sd, container, false);
            return rootView;
        }
    }

=======
>>>>>>> 831ce17d24b66bea47d31099c0d4fb7590ac6c3c
}
