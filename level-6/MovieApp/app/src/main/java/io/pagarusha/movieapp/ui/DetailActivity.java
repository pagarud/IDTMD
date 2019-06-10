package io.pagarusha.movieapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import io.pagarusha.movieapp.R;
import io.pagarusha.movieapp.model.Movie;

public class DetailActivity extends AppCompatActivity {

    private final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w500/";
    private MainViewModel mMainViewModel;

    private ImageView mImageViewBackdrop;
    private ImageView mImageViewPoster;
    private TextView mTextViewTitle;
    private TextView mTextViewReleaseDate;
    private TextView mTextViewRating;
    private TextView mTextViewSynopsis;

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mImageViewBackdrop = findViewById(R.id.image_view_backdrop);
        mImageViewPoster = findViewById(R.id.image_view_poster);
        mTextViewTitle = findViewById(R.id.text_view_title);
        mTextViewReleaseDate = findViewById(R.id.text_view_release_date);
        mTextViewRating = findViewById(R.id.text_view_rating);
        mTextViewSynopsis = findViewById(R.id.text_view_synopsis);

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mMainViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(DetailActivity.this, s, Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mMainViewModel.getMovieById(getIntent().getIntExtra("id", 0));
        mMainViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                mMovie = movie;
                updateUI();
            }
        });
    }

    private void updateUI() {
        Glide.with(DetailActivity.this)
                .load(BASE_IMAGE_URL + mMovie.getBackdropPath())
                .placeholder(R.mipmap.placeholder)
                .into(mImageViewBackdrop);
        Glide.with(DetailActivity.this)
                .load(BASE_IMAGE_URL + mMovie.getPosterPath())
                .placeholder(R.mipmap.placeholder)
                .into(mImageViewPoster);
        mTextViewTitle.setText(mMovie.getTitle());
        mTextViewReleaseDate.setText(mMovie.getReleaseDate());
        mTextViewRating.setText(String.valueOf(mMovie.getVoteAverage()));
        mTextViewSynopsis.setText(mMovie.getOverview());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
