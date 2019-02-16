package com.faysselyabahddou.demomultiselection;

import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * Created by Fayssel Yabahddou on 2/16/19.
 */
public class ContactItemDetails extends ItemDetailsLookup.ItemDetails {

    private final int adapterPosition;

    private final ContactItem selectionKey;

    public ContactItemDetails(int adapterPosition, ContactItem selectionKey) {
        this.adapterPosition = adapterPosition;
        this.selectionKey = selectionKey;
    }

    @Override
    public int getPosition() {
        return adapterPosition;
    }

    @Nullable
    @Override
    public Object getSelectionKey() {
        return selectionKey;
    }
}
