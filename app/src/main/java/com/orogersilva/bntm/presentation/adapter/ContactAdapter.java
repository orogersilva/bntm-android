package com.orogersilva.bntm.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.orogersilva.bntm.R;
import com.orogersilva.bntm.presentation.model.Contact;
import com.orogersilva.bntm.util.BitmapUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ItemViewHolder> {

    // region FIELDS

    private List<Contact> mContacts;
    private ContactItemListener mContactItemListener;

    // endregion

    // region CONSTRUCTORS

    public ContactAdapter(List<Contact> contacts, ContactItemListener contactItemListener) {

        mContacts = contacts;
        mContactItemListener = contactItemListener;
    }

    // endregion

    // region INTERFACES

    public interface ContactItemListener {

        // region METHODS

        void onContactClicked(Contact contact);

        // endregion
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_contact, parent, false);

        v.setTag(new ContactItemPresenter());

        ItemViewHolder itemViewHolder = new ItemViewHolder(v);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ((ContactItemPresenter) holder.itemView.getTag()).presentListItem(holder, mContacts.get(position));
    }

    @Override
    public int getItemCount() {

        return mContacts.size();
    }

    // endregion

    // region UTILITY CLASSES

    class ItemViewHolder extends RecyclerView.ViewHolder implements ItemView<Contact> {

        // region FIELDS

        @BindView(R.id.contactCircularImageView)
        CircularImageView mContactCircularImageView;
        @BindView(R.id.nameTextView)
        TextView mNameTextView;
        @BindView(R.id.phoneTextView)
        TextView mPhoneTextView;

        // endregion

        // region CONSTRUCTORS

        public ItemViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        // endregion

        // region OVERRIDED METHODS

        @Override
        public void setItem(Contact item) {

            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

            Phonenumber.PhoneNumber phoneNumber = null;

            try {

                phoneNumber = phoneUtil.parse(item.getPhone(), "BR");

            } catch (NumberParseException e) {
                Timber.e(e);
            }

            mContactCircularImageView.setImageBitmap(BitmapUtils.getBitmapFromByteArray(
                    item.getPhoto()));
            mNameTextView.setText(item.getName());
            mPhoneTextView.setText(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));
        }

        // endregion
    }

    // endregion
}
