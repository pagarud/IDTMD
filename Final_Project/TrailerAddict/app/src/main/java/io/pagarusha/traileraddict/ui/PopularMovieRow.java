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
import io.pagarusha.traileraddict.db.entity.MovieGist;
import io.pagarusha.traileraddict.ui.model.RecyclerViewRow;


public class PopularMovieRow extends LinearLayout implements RecyclerViewRow<MovieGist> {

    private final String TAG = PopularMovieRow.class.getName();
    private final String BASE_IMG_URL = "https://image.tmdb.org/t/p/";
    private final String BASE_IMG_SIZE = "w154";

    private ImageView mImageView;
    private TextView mTextView;
    private TextView mSubTextView;

    public PopularMovieRow(Context context) {
        super(context);
    }

    public PopularMovieRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PopularMovieRow(Context context, @Nullable AttributeSet attrs, int defStyleAtt) {
        super(context, attrs, defStyleAtt);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = (ImageView) findViewById(R.id.img_rowitem_portrait);
        mTextView = (TextView) findViewById(R.id.tv_rowitem_title);
        mSubTextView = (TextView) findViewById(R.id.tv_rowitem_subtext);

    }

    @Override
    public void showData(MovieGist item) {

        mTextView.setText(item.getOriginalTitle());
        mSubTextView.setText(item.getReleaseDate());
        Log.d(TAG, "showData: " + item.getOriginalTitle());

        // TODO: Glide!!
        Glide.with(this.getContext())
                .load(BASE_IMG_URL + BASE_IMG_SIZE + item.getPosterPath() + "?" + BuildConfig.api_key)
                .placeholder(R.drawable.poster_small)
                .into(mImageView);
    }

}
