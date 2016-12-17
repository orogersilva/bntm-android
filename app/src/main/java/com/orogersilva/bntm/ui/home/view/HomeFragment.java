package com.orogersilva.bntm.ui.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orogersilva.bntm.R;
import com.orogersilva.bntm.ui.home.HomeContract;
import com.orogersilva.bntm.ui.home.HomePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by orogersilva on 12/17/2016.
 */

public class HomeFragment extends Fragment implements HomeContract.View {

    // region FIELDS

    private HomeContract.Presenter mPresenter;

    @BindView(R.id.nameTextView)
    TextView mNameTextView;
    @BindView(R.id.emailTextView)
    TextView mEmailTextView;
    @BindView(R.id.transferMoneyButton)
    Button mTransferMoneyButton;
    @BindView(R.id.moneyTransferHistoryButton)
    Button mMoneyTransferHistoryButton;

    // endregion

    // region CONSTRUCTORS

    public HomeFragment() {}

    // endregion

    // region STATIC METHODS

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

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
    public void showHome(String name, String email) {

        mNameTextView.setText(name);
        mEmailTextView.setText(email);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

        mPresenter = presenter;
    }

    // endregion

    // region EVENT HANDLERS

    @OnClick(R.id.transferMoneyButton)
    public void transferMoney() {


    }

    @OnClick(R.id.moneyTransferHistoryButton)
    public void showMoneyTransferHistory() {


    }

    // endregion
}
