package com.faysselyabahddou.demomultiselection;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;

/**
 * Created by Fayssel Yabahddou on 2/16/19.
 */
public class ContactKeyProvider extends ItemKeyProvider {

    private List<ContactItem> contactItems;

    /**
     * Creates a new provider with the given scope.
     *
     * @param scope Scope can't be changed at runtime.
     */
    protected ContactKeyProvider(int scope, List<ContactItem> contactItems) {
        super(scope);

        this.contactItems = contactItems;
    }

    @Nullable
    @Override
    public Object getKey(int position) {
        return contactItems.get(position);
    }

    @Override
    public int getPosition(@NonNull Object key) {

        return contactItems.indexOf(key);
    }
}
