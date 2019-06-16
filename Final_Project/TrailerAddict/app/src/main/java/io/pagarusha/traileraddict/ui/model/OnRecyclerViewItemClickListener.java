package io.pagarusha.traileraddict.ui.model;

import android.view.View;

public interface OnRecyclerViewItemClickListener<T> extends View.OnClickListener {

    public void onItemClick(T type);
}
