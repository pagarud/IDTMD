package io.pagarusha.geoguess;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.pagarusha.geoguess.adapter.GeoObjectAdapter;
import io.pagarusha.geoguess.model.GeoObject;
import io.pagarusha.geoguess.utilities.Direction;
import io.pagarusha.geoguess.utilities.OnSwipeListener;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private String mWrongAnswer;
    private String mCorrectAnswer;

    private List<GeoObject> mGeoObjects;
    private RecyclerView mGeoRecyclerView;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWrongAnswer = getString(R.string.wrong_answer);
        mCorrectAnswer = getString(R.string.correct_answer);

        mGeoObjects = new ArrayList<>();
        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_IMAGE_IDS.length; i++) {
            mGeoObjects.add(
                    new GeoObject(
                            GeoObject.PRE_DEFINED_GEO_IMAGE_NAMES[i],
                            GeoObject.PRE_DEFINED_GEO_IMAGE_IDS[i],
                            GeoObject.PRE_DEFINED_GEO_ANSWERS[i]
                    )
            );
        }

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mGeoRecyclerView = findViewById(R.id.recyclerView);
        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        mGeoRecyclerView.setHasFixedSize(true);
        GeoObjectAdapter mGeoObjectAdapter = new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mGeoObjectAdapter);

        mGeoRecyclerView.addOnItemTouchListener(this);

        OnSwipeListener mOnSwipeListener = new OnSwipeListener() {

            public boolean onSingleTapUp(MotionEvent e) {
                displayName(e.getX(), e.getY());

                return false;
            }

            @Override
            public boolean onSwipe(Direction direction) {
                if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
                    showAnswer(getInitialX(), getInitialY(), direction);
                }

                return super.onSwipe(direction);
            }
        };

        mGestureDetector = new GestureDetector(this, mOnSwipeListener);
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

    /**
     * Shows a toast message validating the user's answer based on the direction swiped and the
     * location in an image. Swipe left and "in Europe" is correct, swipe right and "not in Europe
     * is correct, all other answers are wrong.
     *
     * @param x The x coordinate where the swipe was initiated.
     * @param y The y coordinate where the swipe was initiated.
     * @param direction The direction that was swiped on the image.
     */
    public void showAnswer(float x, float y, Direction direction) {
        View child = mGeoRecyclerView
                .findChildViewUnder(x, y);

        if (child != null) {
            int mAdapterPosition = mGeoRecyclerView.getChildAdapterPosition(child);
            boolean isEurope = mGeoObjects.get(mAdapterPosition).ismInEurope();

            if ((direction.equals(Direction.LEFT) && isEurope)
                    || (direction.equals(Direction.RIGHT) && !isEurope)) {

                Toast.makeText(this, mCorrectAnswer, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, mWrongAnswer, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Displays a toast message with the location name in the image at a specified position.
     *
     * @param x X coordinate of specified position.
     * @param y Y coordinate of specified position.
     */
    public void displayName(float x, float y) {
        View child = mGeoRecyclerView
                .findChildViewUnder(x, y);

        if (child != null) {
            int mAdapterPosition = mGeoRecyclerView.getChildAdapterPosition(child);
            String name = mGeoObjects.get(mAdapterPosition).getmGeoName();

            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }
    }
}
