package com.faysselyabahddou.demomultiselection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Fayssel Yabahddou on 2/16/19.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;

    private SelectionTracker selectionTracker;
    private List<ContactItem> contactItems;

    public ContactAdapter(Context context, List<ContactItem> contactItems) {
        this.context = context;
        this.contactItems = contactItems;
    }

    public SelectionTracker getSelectionTracker() {
        return selectionTracker;
    }

    public void setSelectionTracker(SelectionTracker selectionTracker) {
        this.selectionTracker = selectionTracker;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        ContactItem item = contactItems.get(position);

        holder.bind(item, selectionTracker.isSelected(item));

    }

    @Override
    public int getItemCount() {

        return contactItems == null ? 0 : contactItems.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements ViewHolderWithDetails {

        private TextView contactName;

        private TextView contactNumber;

        ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.contact_name);

            contactNumber = itemView.findViewById(R.id.contact_number);
        }


        public final void bind(ContactItem item, Boolean isActivated) {

            itemView.setActivated(isActivated);
            contactName.setText(item.getContactName());
            contactNumber.setText(item.getContactNumber());
        }

        @Override
        public ItemDetailsLookup.ItemDetails getItemDetails() {
            return new ContactItemDetails(getAdapterPosition(), contactItems.get(getAdapterPosition()));
        }
    }
}
