package io.pagarusha.movieapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.pagarusha.movieapp.R;
import io.pagarusha.movieapp.model.Movie;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private static final String TAG = "DEBUG";
    public static final int REQUEST_CODE = 1234;
    private final int MIN_RELEASE_YEAR = 1878; // First recorded movie
    private final int MAX_RELEASE_YEAR = Calendar.getInstance().get(Calendar.YEAR) + 8; // Reasonable upper limit...

    private List<Movie> mMovies;
    private Button mButtonGetMovies;
    private EditText mEditTextYear;

    private MainViewModel mMainViewModel;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        mMovies = new ArrayList<>();

        mButtonGetMovies = findViewById(R.id.btn_get_movies);
        mEditTextYear = findViewById(R.id.et_year);

        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                Log.d(TAG, "onSingleTapUp: TRIGGERED!");
                if (child != null) {
                    int adapterPostition = mRecyclerView.getChildAdapterPosition(child);
                    Log.d(TAG, "onSingleTapUp: adapterPos" + adapterPostition);
                    Movie movie = mMovies.get(adapterPostition);
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("id", movie.getId());
                    startActivityForResult(intent, REQUEST_CODE);
                }

                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        mMovieAdapter = new MovieAdapter(this, mMovies);
        mRecyclerView = findViewById(R.id.recycler_view);

        // Display grid with two or more columns depending on viewport width.
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() { // Add Global Layout Listener to calculate span count.
                    @Override
                    public void onGlobalLayout() {
                        mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        gridLayoutManager.setSpanCount(calculateSpanCount());
                        gridLayoutManager.requestLayout();
                    }
                }
        );
        mRecyclerView.setAdapter(mMovieAdapter);
        mRecyclerView.addOnItemTouchListener(this);

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mMainViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT)
                        .show();
            }
        });

        mMainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                mMovies = movies;
                updateUI();
            }
        });

        // Add click listener for search button.
        mButtonGetMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int year = Integer.valueOf(mEditTextYear.getText().toString());
                    if (isValidYear(year)) { // Check if year is in range.
                        mMainViewModel.getMovieListByYear(year);
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Pick a year from " + MIN_RELEASE_YEAR + " to " + MAX_RELEASE_YEAR,
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (Exception e) { // User did not enter an integer number.
                    Toast.makeText(MainActivity.this,
                            "Please enter a valid year",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    /**
     * Check if given year falls within range.
     *
     * @param year the year to be checked.
     * @return <code>true</code> if <code>year</code> is in range, <code>false</code> otherwise.
     */
    private boolean isValidYear(int year) {
        return year >= MIN_RELEASE_YEAR && year <= MAX_RELEASE_YEAR;
    }

    /**
     * Update recyclerview.
     */
    private void updateUI() {
        if (mMovieAdapter == null) {
            mMovieAdapter = new MovieAdapter(this, mMovies);
            mRecyclerView.setAdapter(mMovieAdapter);
        } else {
            mMovieAdapter.swapList(mMovies);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        mGestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Calculate the number of spans for the recycler view based on the recycler view width
     *
     * @return int number of spans.
     */
    private int calculateSpanCount() {
        int viewWidth = mRecyclerView.getMeasuredWidth();
        float posterWidth = getResources().getDimension(R.dimen.poster_width);
        float posterMargin = getResources().getDimension(R.dimen.poster_margin);

        int spanCount = (int) Math.floor(viewWidth / (posterWidth + posterMargin));

        return spanCount >= 1 ? spanCount : 1;
    }
}
