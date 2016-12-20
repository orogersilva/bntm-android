package com.orogersilva.bntm.presentation.screens.transferhistory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.orogersilva.bntm.MainThreadImpl;
import com.orogersilva.bntm.R;
import com.orogersilva.bntm.domain.executor.impl.ThreadExecutor;
import com.orogersilva.bntm.domain.repository.AuthRepository;
import com.orogersilva.bntm.domain.repository.TransferRepository;
import com.orogersilva.bntm.presentation.screens.transferhistory.view.TransferHistoryFragment;
import com.orogersilva.bntm.storage.persistence.AuthLocalDataSource;
import com.orogersilva.bntm.storage.persistence.TransferLocalDataSource;
import com.orogersilva.bntm.sync.AuthRemoteDataSource;
import com.orogersilva.bntm.sync.TransferRemoteDataSource;
import com.orogersilva.bntm.util.ActivityUtils;

/**
 * Created by orogersilva on 12/20/2016.
 */

public class TransferHistoryActivity extends AppCompatActivity {

    // region FIELDS

    private TransferHistoryPresenter mTransferHistoryPresenter;
    private Toolbar mToolbar;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_history);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        TextView toolbarTextView = (TextView) mToolbar.findViewById(R.id.toolbarTitleTextView);
        toolbarTextView.setText(getString(R.string.transfer_historic));

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });

        TransferHistoryFragment transferHistoryFragment = (TransferHistoryFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrameLayout);

        if (transferHistoryFragment == null) {

            transferHistoryFragment = TransferHistoryFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    transferHistoryFragment, R.id.contentFrameLayout);
        }

        mTransferHistoryPresenter = new TransferHistoryPresenter(transferHistoryFragment,
                ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                AuthRepository.getInstance(AuthLocalDataSource.getInstance(this),
                        AuthRemoteDataSource.getInstance()), TransferRepository.getInstance(
                TransferLocalDataSource.getInstance(this), TransferRemoteDataSource.getInstance()));
    }

    // endregion
}
