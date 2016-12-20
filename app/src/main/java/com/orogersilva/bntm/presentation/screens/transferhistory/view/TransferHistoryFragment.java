package com.orogersilva.bntm.presentation.screens.transferhistory.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orogersilva.bntm.R;
import com.orogersilva.bntm.presentation.adapter.ContactTransferHistoryAdapter;
import com.orogersilva.bntm.presentation.model.ContactTransfer;
import com.orogersilva.bntm.presentation.screens.transferhistory.TransferHistoryContract;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orogersilva on 12/20/2016.
 */

public class TransferHistoryFragment extends Fragment implements TransferHistoryContract.View {

    // region FIELDS

    private TransferHistoryContract.Presenter mPresenter;

    private List<ContactTransfer> mContactTransfers = new ArrayList<>();

    private RecyclerView mContactTransfersRecyclerView;
    private RecyclerView.LayoutManager mContactTransfersLayoutManager;
    private ContactTransferHistoryAdapter mContactTransferHistoryAdapter;

    private AVLoadingIndicatorView mLoadingView;

    // endregion

    // region CONSTRUCTORS

    public TransferHistoryFragment() {}

    // endregion

    // region STATIC METHODS

    public static TransferHistoryFragment newInstance() {

        return new TransferHistoryFragment();
    }

    // endregiona

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transfer_history, container, false);

        mLoadingView = (AVLoadingIndicatorView) view.findViewById(R.id.loadingView);

        mContactTransfersRecyclerView = (RecyclerView) view.findViewById(R.id.contactsRecyclerView);
        mContactTransferHistoryAdapter = new ContactTransferHistoryAdapter(mContactTransfers);
        mContactTransfersLayoutManager = new LinearLayoutManager(getContext());

        mContactTransfersRecyclerView.setLayoutManager(mContactTransfersLayoutManager);
        mContactTransfersRecyclerView.setAdapter(mContactTransferHistoryAdapter);

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
    public void setPresenter(TransferHistoryContract.Presenter presenter) {

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
    public void showContactTransfersHistory(List<ContactTransfer> contactTransfers) {

        mContactTransfers.clear();
        mContactTransfers.addAll(contactTransfers);

        mContactTransferHistoryAdapter.notifyDataSetChanged();
    }

    // endregion
}
