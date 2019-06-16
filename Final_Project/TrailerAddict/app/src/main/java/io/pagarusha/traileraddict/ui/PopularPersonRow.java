package io.pagarusha.traileraddict.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import io.pagarusha.traileraddict.BuildConfig;
import io.pagarusha.traileraddict.R;
import io.pagarusha.traileraddict.db.entity.PersonGist;
import io.pagarusha.traileraddict.ui.model.RecyclerViewRow;

public class PopularPersonRow extends LinearLayout implements RecyclerViewRow<PersonGist> {
    private final String TAG = PopularMovieRow.class.getName();
    private final String BASE_IMG_URL = "https://image.tmdb.org/t/p/";
    private final String BASE_IMG_SIZE = "w185";

    private ImageView mImageView;
    private TextView mTextView;

    public PopularPersonRow(Context context) {
        super(context);
    }

    public PopularPersonRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PopularPersonRow(Context context, @Nullable AttributeSet attrs, int defStyleAtt) {
        super(context, attrs, defStyleAtt);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = (ImageView) findViewById(R.id.img_poppeople_portrait);
        mTextView = (TextView) findViewById(R.id.tv_poppeople_title);

    }

    @Override
    public void showData(PersonGist item) {

        mTextView.setText(item.getName());
        Log.d(TAG, "showData: " + item.getName());

        // TODO: Glide!!
        Glide.with(this.getContext())
                .load(BASE_IMG_URL + BASE_IMG_SIZE + item.getProfilePath() + "?" + BuildConfig.api_key)
                .placeholder(R.drawable.poster_small)
                .into(mImageView);
    }
}
