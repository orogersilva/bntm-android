package com.orogersilva.bntm.presentation.screens.transfer.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orogersilva.bntm.R;
import com.orogersilva.bntm.presentation.adapter.ContactAdapter;
import com.orogersilva.bntm.presentation.model.Contact;
import com.orogersilva.bntm.presentation.screens.transfer.TransferContract;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by orogersilva on 12/17/2016.
 */

public class TransferFragment extends Fragment implements TransferContract.View {

    // region FIELDS

    private TransferContract.Presenter mPresenter;

    private List<Contact> mContacts = new ArrayList<>();

    private RecyclerView mContactsRecyclerView;
    private RecyclerView.LayoutManager mContactsLayoutManager;
    private ContactAdapter mContactAdapter;

    private AVLoadingIndicatorView mLoadingView;

    private ContactAdapter.ContactItemListener mContactItemListener = new ContactAdapter.ContactItemListener() {

        @Override
        public void onContactClicked(Contact contact) {

            // TODO: 12/18/2016 TO IMPLEMENT.
        }
    };

    // endregion

    // region CONSTRUCTORS

    public TransferFragment() {}

    // endregion

    // region STATIC METHODS

    public static TransferFragment newInstance() {

        return new TransferFragment();
    }

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transfer, container, false);

        mLoadingView = (AVLoadingIndicatorView) view.findViewById(R.id.loadingView);

        mContactsRecyclerView = (RecyclerView) view.findViewById(R.id.contactsRecyclerView);
        mContactAdapter = new ContactAdapter(mContacts, mContactItemListener);
        mContactsLayoutManager = new LinearLayoutManager(getContext());

        mContactsRecyclerView.setLayoutManager(mContactsLayoutManager);
        mContactsRecyclerView.setAdapter(mContactAdapter);

        return view;
    }

    @Override
    public void onResume() {

        super.onResume();

        mPresenter.resume();
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void setPresenter(TransferContract.Presenter presenter) {

        mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean isActive) {

        if (isActive) {
            mLoadingView.show();
        } else {
            mLoadingView.hide();
        }
    }

    @Override
    public void showContacts(List<Contact> contacts) {

        mContacts.clear();
        mContacts.addAll(contacts);

        mContactAdapter.notifyDataSetChanged();
    }

    // endregion
}
