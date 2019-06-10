package io.pagarusha.apod.ui.model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import android.util.AttributeSet;

public class SquareCardView extends CardView {

    public SquareCardView(@NonNull Context context) {
        super(context);
    }

    public SquareCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Set a square layout
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
