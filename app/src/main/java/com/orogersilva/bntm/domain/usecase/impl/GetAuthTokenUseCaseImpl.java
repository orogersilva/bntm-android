package com.orogersilva.bntm.domain.usecase.impl;

import com.orogersilva.bntm.data.AuthDataSource;
import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;
import com.orogersilva.bntm.domain.repository.AuthRepository;
import com.orogersilva.bntm.domain.usecase.GetAuthTokenUseCase;
import com.orogersilva.bntm.domain.usecase.base.AbstractUseCase;

/**
 * Created by orogersilva on 12/16/2016.
 */

public class GetAuthTokenUseCaseImpl extends AbstractUseCase implements GetAuthTokenUseCase {

    // region FIELDS

    private String mName;
    private String mEmail;

    private GetAuthTokenUseCase.Callback mCallback;
    private AuthRepository mAuthRepository;

    // endregion

    // region CONSTRUCTORS

    public GetAuthTokenUseCaseImpl(String name, String email, Executor threadExecutor,
                                   MainThread mainThread, Callback callback,
                                   AuthRepository authRepository) {

        super(threadExecutor, mainThread);

        mName = name;
        mEmail = email;
        mCallback = callback;
        mAuthRepository = authRepository;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void run() {

        mAuthRepository.getAuthToken(mName, mEmail, new AuthDataSource.GetAuthTokenCallback() {

            @Override
            public void onAuthTokenLoaded(final String authToken) {

                mMainThread.post(new Runnable() {

                    // region OVERRIDED METHODS

                    @Override
                    public void run() {

                        mCallback.onAuthTokenLoaded(authToken);
                    }

                    // endregion
                });
            }

            @Override
            public void onFailed() {

                mMainThread.post(new Runnable() {

                    // region OVERRIDED METHODS

                    @Override
                    public void run() {

                        mCallback.onFailed();
                    }

                    // endregion
                });
            }
        });
    }

    // endregion
}