package io.pagarusha.traileraddict.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import io.pagarusha.traileraddict.BuildConfig;
import io.pagarusha.traileraddict.R;
import io.pagarusha.traileraddict.db.entity.Movie;
import io.pagarusha.traileraddict.remote.MovieApi;
import io.pagarusha.traileraddict.remote.MovieApiService;
import io.pagarusha.traileraddict.viewmodel.MovieViewModel;

public class MovieDetailFragment extends Fragment {

    private final String MOVIE_POSTER_WIDTH = "w154";
    private final String PERSON_POSTER_WIDTH = "w185";

    private final String TAG = MovieDetailFragment.class.getName();
    private final String BUNDLE_MOVIE_ID = "id";
    private int mMovieId;
    private MovieViewModel mMovieViewModel;

    private ImageView mMoviePoster;
    private TextView mMovieTitle;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mSummary;

    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieId = -1;
        Bundle b = getArguments();
        if (b != null) {
            mMovieId = b.getInt(BUNDLE_MOVIE_ID);
        }

        mMovie = new Movie();
//        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
//        mMovieViewModel.getMovieById(mMovieId).observe(this, new Observer<Movie>() {
//            @Override
//            public void onChanged(Movie movie) {
//                mMovie = movie;
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);



        mMoviePoster = view.findViewById(R.id.img_moviedetail_poster);
        mMovieTitle = view.findViewById(R.id.title_moviedetail);
        mReleaseDate = view.findViewById(R.id.text_moviedetail_releasedate);
        mVoteAverage = view.findViewById(R.id.tv_moviedetail_voteaverage);
        mSummary =  view.findViewById(R.id.tv_moviedetail_summary);

//        Glide.with(this.getContext())
//                .load(MovieApi.BASE_IMAGE_URL + MOVIE_POSTER_WIDTH + mMovie.getPosterPath() + "?" + BuildConfig.api_key)
//                .placeholder(R.drawable.poster_small)
//                .into(mMoviePoster);
//        mMovieTitle.setText(mMovie.getTitle().toString());
//        mReleaseDate.setText(mMovie.getReleaseDate().toString());
//        mVoteAverage.setText(mMovie.getVoteAverage().toString());
//        mSummary.setText(mMovie.getOverview().toString());

        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }
}
