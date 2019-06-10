package io.pagarusha.apod.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.pagarusha.apod.R;
import io.pagarusha.apod.model.Apod;
import io.pagarusha.apod.model.DummyApod;

/**
 * Class contains the recyclerview implementation for the image gallery.
 */
public class GalleryFragment extends Fragment {

    public GalleryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, parent, false);

        // 1. Get reference to recyclerview
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_gallery);

        // 2. Set layoutmanager
        @SuppressLint("WrongConstant")
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set data for recycler
        // TODO: Use viewmodel to populate list
        List<Apod> apods = new ArrayList<>();
        apods.add(DummyApod.getApod());
        apods.add(DummyApod.getApod());
        apods.add(DummyApod.getApod());
        apods.add(DummyApod.getApod());

        // 3. Create an adapter
        GalleryAdapter adapter = new GalleryAdapter(apods, getActivity());

        // 4. Set adapter
        recyclerView.setAdapter(adapter);

        // 5. Set item animator to default animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;

    }
}
