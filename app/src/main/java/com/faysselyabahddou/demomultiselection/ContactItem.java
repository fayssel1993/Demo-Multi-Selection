package com.faysselyabahddou.demomultiselection;

import androidx.annotation.Nullable;

/**
 * Created by Fayssel Yabahddou on 2/16/19.
 */
public class ContactItem {

    private int id;

    private String contactName;

    private String contactNumber;

    public ContactItem() {
    }

    public ContactItem(int id, String contactName, String contactNumber) {
        this.id = id;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null)
            return false;

        ContactItem itemCompare = (ContactItem) obj;
        if(itemCompare.getContactName().equals(this.getContactName()))
            return true;

        return false;    }
}
