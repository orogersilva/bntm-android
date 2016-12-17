package com.orogersilva.bntm.domain.usecase.impl;

import com.orogersilva.bntm.AuthDataSource;
import com.orogersilva.bntm.domain.executor.Executor;
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
                                   Callback callback, AuthRepository authRepository) {

        super(threadExecutor);

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
            public void onAuthTokenLoaded(String authToken) {

                mCallback.onAuthTokenLoaded(authToken);
            }

            @Override
            public void onFailed() {

                mCallback.onFailed();
            }
        });
    }

    // endregion
}