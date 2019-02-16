package com.faysselyabahddou.demomultiselection;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.OnDragInitiatedListener;
import androidx.recyclerview.selection.OnItemActivatedListener;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    MenuItem selectedItemCount;

    SelectionTracker selectionTracker;

    ActionMode actionMode;

    private ContactAdapter contactAdapter;

    private List<ContactItem> contactItems;

    androidx.appcompat.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        actionBar = getSupportActionBar();

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mockData();

        contactAdapter = new ContactAdapter(this, contactItems);

        recyclerView.setAdapter(contactAdapter);

        selectionTracker = new SelectionTracker.Builder<>(
                "my-selection-id",
                recyclerView,
                new ContactKeyProvider(1, contactItems),
                new ContactItemLookup(recyclerView),
                StorageStrategy.createLongStorage()
        )
                .withOnItemActivatedListener(new OnItemActivatedListener<Long>() {
                    @Override
                    public boolean onItemActivated(@NonNull ItemDetailsLookup.ItemDetails<Long> item, @NonNull MotionEvent e) {
                        Log.d(TAG, "Selected ItemId: " + item.toString());
                        return true;
                    }
                })
                .withOnDragInitiatedListener(new OnDragInitiatedListener() {
                    @Override
                    public boolean onDragInitiated(@NonNull MotionEvent e) {
                        Log.d(TAG, "onDragInitiated");
                        return true;
                    }
                })
                .build();

        contactAdapter.setSelectionTracker(selectionTracker);

        selectionTracker.addObserver(new SelectionTracker.SelectionObserver() {
            @Override
            public void onItemStateChanged(@NonNull Object key, boolean selected) {
                super.onItemStateChanged(key, selected);
            }

            @Override
            public void onSelectionRefresh() {
                super.onSelectionRefresh();
            }

            @Override
            public void onSelectionChanged() {
                super.onSelectionChanged();

                if (selectionTracker.hasSelection() && actionMode == null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionMode = startSupportActionMode(new ActionModeController(MainActivity.this, selectionTracker));
                    setMenuItemTitle(selectionTracker.getSelection().size());
                } else if (!selectionTracker.hasSelection() && actionMode != null) {
                    actionMode.finish();
                    actionMode = null;
                } else {
                    setMenuItemTitle(selectionTracker.getSelection().size());
                }
                Iterator<ContactItem> itemIterable = selectionTracker.getSelection().iterator();
                while (itemIterable.hasNext()) {
                    Log.i(TAG, itemIterable.next().getContactName());
                }
            }

            @Override
            public void onSelectionRestored() {
                super.onSelectionRestored();
            }
        });

        if (savedInstanceState != null) {
            selectionTracker.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        selectionTracker.onSaveInstanceState(outState);
    }

    private void setMenuItemTitle(int size) {
        selectedItemCount.setTitle("" + size);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        selectedItemCount = menu.findItem(R.id.action_item_count);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_delete:

                break;
            case R.id.action_clear:

                selectionTracker.clearSelection();
                break;
            case android.R.id.home:

                selectionTracker.clearSelection();
                actionBar.setDisplayHomeAsUpEnabled(false);
                break;
        }

        return true;
    }

    private void mockData() {

        contactItems = new ArrayList<>();

        ContactItem item;

        for (int i = 0; i < 30; i++) {
            if (i < 10) {
                item = new ContactItem(i + 1, "fayssel" + (i + 1), "062081985" + i);
            } else {
                item = new ContactItem(i + 1, "fayssel" + (i + 1), "06208198" + i);
            }
            contactItems.add(item);
        }

    }
}
