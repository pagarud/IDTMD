package io.pagarusha.gamebacklog.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import io.pagarusha.gamebacklog.R;
import io.pagarusha.gamebacklog.model.Game;

public class EditGameActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mPlatform;
    private Spinner mSpinner;
    private MainViewModel mMainViewModel;
    private Game mGame;

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

        Intent intent = getIntent();

        mTitle.setText(intent.getStringExtra("title"));
        mPlatform.setText(intent.getStringExtra("platform"));
        mSpinner.setSelection(adapter.getPosition(intent.getStringExtra("status")));

        mGame = new Game();
        mGame.setId(intent.getLongExtra("id", -1));

        FloatingActionButton fab = findViewById(R.id.fab_add_game);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString();
                String platform = mPlatform.getText().toString();
                String status = mSpinner.getSelectedItem().toString();

                mGame.setId(intent.getLongExtra("id", -1));
                mGame.setTitle(title);
                mGame.setPlatform(platform);
                mGame.setStatus(status);
                mGame.setDate(new Date());
                if (mGame.getId() != -1) {
                    mMainViewModel.update(mGame);
                }
                finish();
            }
        });
    }
}
