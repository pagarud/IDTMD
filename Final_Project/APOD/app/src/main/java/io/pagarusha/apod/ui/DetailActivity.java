package io.pagarusha.apod.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import io.pagarusha.apod.R;
import io.pagarusha.apod.model.Apod;
import io.pagarusha.apod.model.DummyApod;

public class DetailActivity extends AppCompatActivity {

    private ImageView apodImage;
    private TextView apodTitle;
    private TextView apodDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String value = getIntent().getExtras().getString("date");
        getSupportActionBar().setTitle(value);

        // TODO: viewmodel to get apod by `value`.

        Apod apod = DummyApod.getApod(); // TODO: remove dummy data

        // TODO: start detail fragment
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_detailview_share) {
            // TODO: implement sharing
            return true;
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
