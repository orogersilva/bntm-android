package com.orogersilva.bntm.presentation;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.orogersilva.bntm.R;
import com.orogersilva.bntm.presentation.model.Contact;
import com.orogersilva.bntm.presentation.screens.transfer.TransferContract;
import com.orogersilva.bntm.util.BitmapUtils;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by orogersilva on 12/19/2016.
 */

public class CustomTransferDialog extends Dialog {

    // region FIELDS

    private TransferContract.Presenter mPresenter;
    private Contact mContact;

    private ViewGroup mContentLayout;
    private AVLoadingIndicatorView mLoadingView;

    @BindView(R.id.contactCircularImageView)
    CircularImageView mContactCircularImageView;
    @BindView(R.id.nameTextView)
    TextView mNameTextView;
    @BindView(R.id.phoneTextView)
    TextView mPhoneTextView;
    @BindView(R.id.moneyCurrencyEditText)
    CurrencyEditText mMoneyCurrencyEditText;
    @BindView(R.id.sendButton)
    Button mSendButton;
    @BindView(R.id.closeButton)
    Button mCloseButton;


    // endregion

    // region CONSTRUCTORS

    public CustomTransferDialog(FragmentActivity activity, TransferContract.Presenter presenter, Contact contact) {

        super(activity);

        mPresenter = presenter;
        mContact = contact;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_transfer);

        ButterKnife.bind(this);

        mContentLayout = (RelativeLayout) findViewById(R.id.contentLayout);
        mLoadingView = (AVLoadingIndicatorView) findViewById(R.id.loadingView);


        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        Phonenumber.PhoneNumber phoneNumber = null;

        try {

            phoneNumber = phoneUtil.parse(mContact.getPhone(), "BR");

        } catch (NumberParseException e) {
            Timber.e(e);
        }

        mContactCircularImageView.setImageBitmap(BitmapUtils.getBitmapFromByteArray(mContact.getPhoto()));

        mNameTextView.setText(mContact.getName());
        mPhoneTextView.setText(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));
    }

    // endregion

    // region EVENT HANDLERS

    @OnClick(R.id.sendButton)
    public void sendMoney(){

        mContentLayout.setVisibility(View.GONE);
        mLoadingView.show();

        double moneyValue = (double) mMoneyCurrencyEditText.getRawValue() / 100;

        mPresenter.sendMoney(mContact, moneyValue);
    }

    @OnClick(R.id.closeButton)
    public void closeDialog() {

        dismiss();
    }

    // endregion
}
