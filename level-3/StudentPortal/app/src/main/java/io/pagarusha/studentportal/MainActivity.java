package io.pagarusha.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import io.pagarusha.studentportal.model.Portal;

public class MainActivity extends AppCompatActivity {

    private static final int REQUESTCODE = 1234;
    private final String TAG = "DEBUG";

    private PortalAdapter portalAdapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        portalAdapter = new PortalAdapter(this);
        gridView.setAdapter(portalAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                Portal portal = (Portal) portalAdapter.getItem(position);
                String url = portal.getUrl();
                intent.putExtra("url", url);
                startActivityForResult(intent, REQUESTCODE);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check if result code is correct
        if (resultCode == Activity.RESULT_OK) {

            // Check if the request code is correct
            if (requestCode == REQUESTCODE) {
                Portal portal;
                portal = data.getParcelableExtra("portal");
                portalAdapter.add(portal);
                portalAdapter.notifyDataSetChanged();
            }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
