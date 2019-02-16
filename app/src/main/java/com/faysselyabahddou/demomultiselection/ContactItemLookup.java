package com.faysselyabahddou.demomultiselection;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Fayssel Yabahddou on 2/16/19.
 */
public class ContactItemLookup extends ItemDetailsLookup {

    private final RecyclerView recyclerView;

    public ContactItemLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails getItemDetails(@NonNull MotionEvent e) {

        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());

        if (view != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);

            if (viewHolder instanceof ContactAdapter.ContactViewHolder) {

                return ((ContactAdapter.ContactViewHolder) viewHolder).getItemDetails();
            }
        }

        return null;
    }
}
