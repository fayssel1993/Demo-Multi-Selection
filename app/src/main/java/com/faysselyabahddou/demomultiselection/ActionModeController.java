package com.faysselyabahddou.demomultiselection;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.selection.SelectionTracker;

/**
 * Created by Fayssel Yabahddou on 2/16/19.
 */
public class ActionModeController implements ActionMode.Callback {

    private final Context context;

    private final SelectionTracker selectionTracker;

    public ActionModeController(Context context, SelectionTracker selectionTracker) {
        this.context = context;
        this.selectionTracker = selectionTracker;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

        selectionTracker.clearSelection();
    }
}
