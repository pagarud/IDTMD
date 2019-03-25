package io.pagarusha.studentportal;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.pagarusha.studentportal.model.Portal;

public class PortalAdapter extends BaseAdapter {

    private List<Portal> portals;
    private final Context context;

    public PortalAdapter(Context context) {

        this.context = context;
        this.portals = new ArrayList<>();
    }

    public void add(Portal portal) {
        this.portals.add(portal);
    }

    @Override
    public int getCount() {

        return portals.size();
    }

    @Override
    public Object getItem(int position) {

        return portals.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView title = new TextView(context);
//        title.setText(portals.get(position).getTitle());
//        title.setTextSize(18);
//        title.setTypeface(Typeface.DEFAULT_BOLD);
//        title.setGravity(Gravity.CENTER);

        TextView portal = new TextView(context);
        portal.setText(portals.get(position).getTitle());

        return portal;
    }
}
