package com.iantje.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener  {

    public static final String EXTRA_PORTAL_ITEM_INTENT = "PORTAL_ITEM";
    public static final String EXTRA_NAME_INTENT = "PORTAL_NAME";
    public static final String EXTRA_URL_INTENT = "PORTAL_URL";

    public static final int ADD_ITEM_REQUESTCODE = 1337;

    private RecyclerView rView;
    private List<PortalItem> portalItems;
    private PortalItemAdapter itemAdapter;

    private GestureDetector _gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        portalItems = new ArrayList<>();

        rView = findViewById(R.id.portalRecycler);
        rView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));

        itemAdapter = new PortalItemAdapter(portalItems);
        rView.setAdapter(itemAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPortalActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUESTCODE);
            }
        });

        _gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        rView.addOnItemTouchListener(this);
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

    private void updateUI() {
        if (itemAdapter == null) {
            itemAdapter = new PortalItemAdapter(portalItems);
            rView.setAdapter(itemAdapter);
        } else {
            itemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == ADD_ITEM_REQUESTCODE) {
            if(resultCode == RESULT_OK) {
                portalItems.add((PortalItem)data.getParcelableExtra(EXTRA_PORTAL_ITEM_INTENT));
                updateUI();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        int adapterPosition = recyclerView.getChildAdapterPosition(child);

        if(child != null && _gestureDetector.onTouchEvent(motionEvent)) {
            Intent intent = new Intent(this, ViewPortalActivity.class);
            intent.putExtra(EXTRA_URL_INTENT, portalItems.get(adapterPosition).url);
            startActivity(intent);
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
