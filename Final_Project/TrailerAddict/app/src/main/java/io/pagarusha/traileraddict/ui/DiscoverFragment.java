package io.pagarusha.traileraddict.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.pagarusha.traileraddict.R;
import io.pagarusha.traileraddict.db.entity.MovieGist;
import io.pagarusha.traileraddict.db.entity.PersonGist;
import io.pagarusha.traileraddict.ui.model.OnRecyclerViewItemClickListener;
import io.pagarusha.traileraddict.viewmodel.MovieViewModel;

public class DiscoverFragment extends Fragment implements OnRecyclerViewItemClickListener {

    private final String TAG = DiscoverFragment.class.getName();
    private final String BUNDLE_MOVIE_ID = "id";

    private MovieViewModel mMovieViewModel;
    private List<MovieGist> mPopularMovies;
    private List<PersonGist> mPopularPeople;
    private RecyclerViewAdapter<MovieGist> mMovieAdapter;
    private RecyclerViewAdapter<PersonGist> mPersonAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPopularMovies = new ArrayList<>();
        mPopularPeople = new ArrayList<>();

        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        RecyclerView rcMovies = (RecyclerView) view.findViewById(R.id.rc_movies);
        mMovieAdapter = new RecyclerViewAdapter<>((ArrayList) mPopularMovies, this, R.layout.movie_row);
        rcMovies.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rcMovies.setAdapter(mMovieAdapter);

        mMovieViewModel.getPopularMovies().observe(this, new Observer<List<MovieGist>>() {
            @Override
            public void onChanged(List<MovieGist> movies) {
                mPopularMovies = movies;
//                updateUI(mPopularMovies);
                mMovieAdapter.setItems(mPopularMovies);
            }
        });

        RecyclerView rcPeople = (RecyclerView) view.findViewById(R.id.rc_people);
        mPersonAdapter = new RecyclerViewAdapter<>((ArrayList) mPopularPeople, this, R.layout.person_row);
        rcPeople.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rcPeople.setAdapter(mPersonAdapter);

        mMovieViewModel.getPopularPeople().observe(this, new Observer<List<PersonGist>>() {
            @Override
            public void onChanged(List<PersonGist> people) {
                mPopularPeople = people;
                mPersonAdapter.setItems(mPopularPeople);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(Object type) {
        if (type instanceof MovieGist) {
            MovieGist mg = (MovieGist) type;

            MovieDetailFragment fragment = new MovieDetailFragment();

            Bundle bundle = new Bundle();
            bundle.putInt(BUNDLE_MOVIE_ID, Integer.valueOf(mg.getId()));
            fragment.setArguments(bundle);

            Log.d(TAG, "onItemClick: movie_id" + fragment.getArguments().getInt(BUNDLE_MOVIE_ID));
            startDetailFragment(fragment);

        } else if (type instanceof PersonGist) {
            PersonGist pg = (PersonGist) type;
        }

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void startDetailFragment(Fragment nextFragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_contentmain_fragmentcontainer, nextFragment, "movieDetailFragment")
                .addToBackStack(null)
                .commit();
    }
}
