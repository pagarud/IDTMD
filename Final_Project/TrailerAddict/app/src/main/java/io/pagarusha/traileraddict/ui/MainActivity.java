package io.pagarusha.traileraddict.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.pagarusha.traileraddict.R;
import io.pagarusha.traileraddict.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();

    private TextView mTextMessage;
    private MovieViewModel mMovieViewModel;

    // TODO: link to fragments
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    mTextMessage.setText(R.string.nav_discover);
                    replaceFragment(new DiscoverFragment());
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.nav_search);
                    return true;
                case R.id.navigation_favorites:
                    mTextMessage.setText(R.string.nav_favorites);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        replaceFragment(new DiscoverFragment());

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framelayout_contentmain_fragmentcontainer, fragment);
        transaction.commit();
    }
}
