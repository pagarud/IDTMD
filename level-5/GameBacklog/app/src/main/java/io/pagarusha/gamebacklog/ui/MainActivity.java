package io.pagarusha.gamebacklog.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import io.pagarusha.gamebacklog.R;
import io.pagarusha.gamebacklog.model.Game;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    // Instance variables
    private static final int REQUESTCODE = 1234;

    private List<Game> mGames;
    private MainViewModel mViewModel;
    private GameAdapter mGameAdapter;
    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;
    private Game mRecentlyDeletedGame;
    private List<Game> mRecentlyDeletedGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGames = new ArrayList<>();

        mGameAdapter = new GameAdapter(mGames);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(mGameAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addOnItemTouchListener(this);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> games) {
                mGames = games;
                updateUI();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddGameActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });

        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                // TODO: start edit activity, pass id of chosen game
                View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    int adapterPos = mRecyclerView.getChildAdapterPosition(child);
                    Game game = mGames.get(adapterPos);
                    Intent intent = new Intent(MainActivity.this, EditGameActivity.class);
                    intent.putExtra("id", game.getId());
                    intent.putExtra("title", game.getTitle());
                    intent.putExtra("platform", game.getPlatform());
                    intent.putExtra("status", game.getStatus());
                    startActivityForResult(intent, REQUESTCODE);
                }

                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                        int position = viewHolder.getAdapterPosition();
                        Game game = mGames.get(position);
                        deleteGame(game);

                    }
                }
        );
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void deleteGame(Game game) {
        mRecentlyDeletedGame = game;
        mViewModel.delete(game);
        showUndoSnackbar();
    }

    private void deleteAllGames() {
        mRecentlyDeletedGames = mGames;
        mViewModel.delete(mGames);
        showUndoSnackbar();
    }

    private void showUndoSnackbar() {
        String snackbarMessage = mRecentlyDeletedGame == null
                ? getResources().getString(R.string.snackbar_text_all_items_deleted)
                : getResources().getString(R.string.snackbar_text_one_item_deleted);
        View contextView = findViewById(R.id.coordinator);
        Snackbar snackbar = Snackbar.make(contextView, snackbarMessage,
                Snackbar.LENGTH_LONG);

        snackbar.setAction(R.string.snackbar_undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecentlyDeletedGame == null) {
                    mViewModel.insert(mRecentlyDeletedGames);
                    mRecentlyDeletedGames = null;
                } else {
                    mViewModel.insert(mRecentlyDeletedGame);
                    mRecentlyDeletedGame = null;
                }
            }
        });
        snackbar.show();
    }

    private void updateUI() {
        if (mGameAdapter == null) {
            mGameAdapter = new GameAdapter(mGames);
            mRecyclerView.setAdapter(mGameAdapter);
        } else {
            mGameAdapter.swapList(mGames);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_item) {
            deleteAllGames();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        mGestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
