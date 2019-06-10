package io.pagarusha.apod.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import io.pagarusha.apod.R;
import io.pagarusha.apod.model.Apod;

public class ApodFragment extends Fragment {

    private ApodViewModel apodViewModel;
    private Apod apod;

    private ImageView apodImage;
    private TextView title;
    private TextView explanation;
    private TextView credits;

    private BottomSheetBehavior btb;
    private ImageView toggle;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apod, parent, false);

        apodViewModel = ViewModelProviders.of(this).get(ApodViewModel.class);
        apod = new Apod();

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_apod_loadimage);

        apodViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT)
                        .show();
            }
        });

        apodViewModel.getApod().observe(this, new Observer<Apod>() {
            @Override
            public void onChanged(Apod data) {
                apod = data;
                setViews();
            }
        });

        apodViewModel.getApodByDate("2019-06-08");

        // Instantiate views
        apodImage = rootView.findViewById(R.id.imageview_apod);
        title = rootView.findViewById(R.id.text_apod_explanationtitle);
        explanation = rootView.findViewById(R.id.text_apod_explanation);
        credits = rootView.findViewById(R.id.text_apod_imagecredits);

        // TODO: get an Apod.class instance from ViewModel and populate views

        toggle = rootView.findViewById(R.id.btn_apod_bottomsheettoggle);
        LinearLayout linearLayout = rootView.findViewById(R.id.linearlayout_apod_bottomsheetbehavior);
        btb = BottomSheetBehavior.from(linearLayout);

        // Set listener on explanation toggle
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btb.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    btb.setState(BottomSheetBehavior.STATE_EXPANDED);
                    toggle.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                } else {
                    btb.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    toggle.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
            }
        });

        // Set callback for bottomsheet state change
        btb.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    toggle.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else  {
                    toggle.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        return rootView;
    }

    private void setViews() {
        // Set view properties

        title.setText(apod.getTitle());
        explanation.setText(apod.getExplanation());
        credits.setText(getString(R.string.apod_copyright) + " " + apod.getCopyright());

        Glide.with(this)
                .load(apod.getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(apodImage);
    }
}
