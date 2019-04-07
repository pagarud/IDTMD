package io.pagarusha.gamebacklog.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import io.pagarusha.gamebacklog.R;
import io.pagarusha.gamebacklog.model.Game;

public class AddGameActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mPlatform;
    private Spinner mSpinner;
    private MainViewModel mMainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game_activity);

        mTitle = findViewById(R.id.ti_title);
        mPlatform = findViewById(R.id.ti_platform);
        mSpinner = findViewById(R.id.spinner_status);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.game_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        FloatingActionButton fab = findViewById(R.id.fab_add_game);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString();
                String platform = mPlatform.getText().toString();
                String status = mSpinner.getSelectedItem().toString();

                Game game = new Game(title, platform, status);
                mMainViewModel.insert(game);
                finish();
            }
        });
    }
}
