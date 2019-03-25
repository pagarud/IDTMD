package io.pagarusha.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import io.pagarusha.studentportal.model.Portal;

public class AddActivity extends AppCompatActivity {

    public static final String PORTAL_KEY = "portal";
    private final String TAG = AddActivity.class.getName();

    private EditText mUrl;
    private EditText mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUrl = findViewById(R.id.editText_url);
        mTitle = findViewById(R.id.editText_title);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String url = mUrl.getText().toString();
                String title = mTitle.getText().toString();
                Portal portal = new Portal(title, url);

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.putExtra("portal", portal);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

}
