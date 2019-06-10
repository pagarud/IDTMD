package io.pagarusha.apod.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import io.pagarusha.apod.R;

public class ApodFragment extends Fragment {

    private ImageView apodImage;
    private TextView title;
    private TextView explanation;
    private TextView credits;

    private BottomSheetBehavior btb;
    private ImageView toggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apod, parent, false);

        // TODO: get today's APOD instance and populate views

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

}
