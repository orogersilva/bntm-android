package com.orogersilva.bntm.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyTextFormatter;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.orogersilva.bntm.R;
import com.orogersilva.bntm.presentation.model.ContactTransfer;
import com.orogersilva.bntm.util.BitmapUtils;

import java.util.Currency;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by orogersilva on 12/20/2016.
 */

public class ContactTransferHistoryAdapter extends RecyclerView.Adapter<ContactTransferHistoryAdapter.ItemViewHolder> {

    // region FIELDS

    private List<ContactTransfer> mContactTransfers;

    // endregion

    // region CONSTRUCTORS

    public ContactTransferHistoryAdapter(List<ContactTransfer> contactTransfers) {

        mContactTransfers = contactTransfers;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_contact_transfer, parent, false);

        v.setTag(new ContactTransferHistoryItemPresenter());

        ItemViewHolder itemViewHolder = new ItemViewHolder(v);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ((ContactTransferHistoryItemPresenter) holder.itemView.getTag()).presentListItem(holder,
                mContactTransfers.get(position));
    }

    @Override
    public int getItemCount() {

        return mContactTransfers.size();
    }

    // endregion

    // region UTILITY CLASSES

    class ItemViewHolder extends RecyclerView.ViewHolder implements ItemView<ContactTransfer> {

        // region FIELDS

        @BindView(R.id.contactCircularImageView)
        CircularImageView mContactCircularImageView;
        @BindView(R.id.nameTextView)
        TextView mNameTextView;
        @BindView(R.id.phoneTextView)
        TextView mPhoneTextView;
        @BindView(R.id.transferredMoneyValueTextView)
        TextView mTransferredMoneyValueTextView;

        // endregion

        // region CONSTRUCTORS

        public ItemViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        // endregion

        // region OVERRIDED METHODS

        @Override
        public void setItem(ContactTransfer item) {

            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

            Phonenumber.PhoneNumber phoneNumber = null;

            try {

                phoneNumber = phoneUtil.parse(item.getContactPhone(), "BR");

            } catch (NumberParseException e) {
                Timber.e(e);
            }

            mContactCircularImageView.setImageBitmap(
                    BitmapUtils.getBitmapFromByteArray(item.getContactPhoto()));
            mNameTextView.setText(item.getContactName());
            mPhoneTextView.setText(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));

            Locale locale = new Locale("pt", "BR");

            mTransferredMoneyValueTextView.setText(CurrencyTextFormatter.formatText(
                    Long.toString(item.getTransferredTotalMoney()), Currency.getInstance(locale), locale));
        }

        // endregion
    }

    // endregion
}
