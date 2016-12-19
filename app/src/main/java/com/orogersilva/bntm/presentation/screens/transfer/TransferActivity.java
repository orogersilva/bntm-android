package com.orogersilva.bntm.presentation.screens.transfer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.orogersilva.bntm.MainThreadImpl;
import com.orogersilva.bntm.R;
import com.orogersilva.bntm.domain.executor.impl.ThreadExecutor;
import com.orogersilva.bntm.domain.repository.AuthRepository;
import com.orogersilva.bntm.domain.repository.ContactRepository;
import com.orogersilva.bntm.domain.repository.TransferRepository;
import com.orogersilva.bntm.presentation.screens.transfer.view.TransferFragment;
import com.orogersilva.bntm.storage.persistence.AuthLocalDataSource;
import com.orogersilva.bntm.storage.persistence.ContactLocalDataSource;
import com.orogersilva.bntm.sync.AuthRemoteDataSource;
import com.orogersilva.bntm.sync.TransferRemoteDataSource;
import com.orogersilva.bntm.util.ActivityUtils;

/**
 * Created by orogersilva on 12/17/2016.
 */

public class TransferActivity extends AppCompatActivity {

    // region FIELDS

    private TransferPresenter mTransferPresenter;
    private Toolbar mToolbar;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        TextView toolbarTextView = (TextView) mToolbar.findViewById(R.id.toolbarTitleTextView);
        toolbarTextView.setText(getString(R.string.send_money));

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });

        TransferFragment transferFragment = (TransferFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrameLayout);

        if (transferFragment == null) {

            transferFragment = TransferFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), transferFragment,
                    R.id.contentFrameLayout);
        }

        mTransferPresenter = new TransferPresenter(transferFragment, ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), AuthRepository.getInstance(
                AuthLocalDataSource.getInstance(this), AuthRemoteDataSource.getInstance()),
                ContactRepository.getInstance(ContactLocalDataSource.getInstance(this)),
                TransferRepository.getInstance(TransferRemoteDataSource.getInstance()));
    }

    // endregion
}
