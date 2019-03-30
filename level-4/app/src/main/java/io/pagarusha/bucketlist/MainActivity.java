package io.pagarusha.bucketlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.pagarusha.bucketlist.model.BucketListItem;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private static final int REQUESTCODE = 1234;
    private RecyclerView rvBucketList;
    private BucketListAdapter adapter;
    private ListItemRoomDatabase db;
    private Executor executor = Executors.newSingleThreadExecutor();
    private List<BucketListItem> bucketList;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = ListItemRoomDatabase.getDatabase(this);
        bucketList = new ArrayList<>();

        adapter = new BucketListAdapter(this, bucketList);
        rvBucketList = findViewById(R.id.rv_bucket_list);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBucketList.setLayoutManager(mLayoutManager);
        rvBucketList.setAdapter(adapter);
        rvBucketList.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );

        rvBucketList.addOnItemTouchListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = rvBucketList.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    int adapterPosition = rvBucketList.getChildAdapterPosition(child);

                    // Update list item
                    BucketListItem item = bucketList.get(adapterPosition);
                    item.setChecked(!item.isChecked());
                    updateBucketListItem(item);
                }

                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = rvBucketList.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    int adapterPostition = rvBucketList.getChildAdapterPosition(child);
                    delete(bucketList.get(adapterPostition));
                }

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }

        });

        getAllItems();
    }

    // Receive input from AddItemActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check if result code is correct
        if (resultCode == Activity.RESULT_OK) {

            // Check if the request code is correct
            if (requestCode == REQUESTCODE) {
                BucketListItem item = new BucketListItem(
                        data.getStringExtra("title"),
                        data.getStringExtra("description"),
                        false
                );

                insertBucketListItem(item);
            }
        }
    }

    // CRUD operations
    private void updateBucketListItem(final BucketListItem item) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.itemDao().update(item);
                getAllItems();
            }
        });
    }

    private void insertBucketListItem(final BucketListItem item) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.itemDao().insert(item);
                getAllItems();
            }
        });
    }

    private void delete(final BucketListItem item) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.itemDao().delete(item);
                getAllItems();
            }
        });
    }

    private void deleteAll(final List<BucketListItem> items) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.itemDao().delete(items);
                getAllItems();
            }
        });
    }

    private void getAllItems() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final List<BucketListItem> items = db.itemDao().getAllListItems();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI(items);
                    }
                });
            }
        });
    }

    private void updateUI(List<BucketListItem> items) {
        bucketList.clear();
        bucketList.addAll(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Checking the item id of our menu item
        if (item.getItemId() == R.id.action_delete_item) {

            // Deleting all items and notifying our list adapter of the change
            deleteAll(bucketList);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Gesture detection
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
