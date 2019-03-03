package io.pagarusha.geoguess.model;

import io.pagarusha.geoguess.R;

public class GeoObject {

    private String mGeoName;
    private int mGeoImageId;
    private boolean mInEurope;

    public static final String[] PRE_DEFINED_GEO_IMAGE_NAMES = {
            "Denmark",
            "Canada",
            "Bangladesh",
            "Kazachstan",
            "Colombia",
            "Poland",
            "Malta",
            "Thailand"
    };

    public static final int[] PRE_DEFINED_GEO_IMAGE_IDS = {
            R.drawable.img1_yes_denmark,
            R.drawable.img2_no_canada,
            R.drawable.img3_no_bangladesh,
            R.drawable.img4_yes_kazachstan,
            R.drawable.img5_no_colombia,
            R.drawable.img6_yes_poland,
            R.drawable.img7_yes_malta,
            R.drawable.img8_no_thailand
    };

    public static final boolean[] PRE_DEFINED_GEO_ANSWERS = {
            true,
            false,
            false,
            true,
            false,
            true,
            true,
            false
    };

    public GeoObject(String mGeoName, int mGeoImageId, boolean mInEurope) {
        this.mGeoName = mGeoName;
        this.mGeoImageId = mGeoImageId;
        this.mInEurope = mInEurope;
    }

    public int getmGeoImageId() {
        return mGeoImageId;
    }

    public void setmGeoImageId(int mGeoImageId) {
        this.mGeoImageId = mGeoImageId;
    }

    public String getmGeoName() {
        return mGeoName;
    }

    public void setmGeoName(String mGeoName) {
        this.mGeoName = mGeoName;
    }

    public boolean ismInEurope() {
        return mInEurope;
    }

    public void setmInEurope(boolean mInEurope) {
        this.mInEurope = mInEurope;
    }
}
