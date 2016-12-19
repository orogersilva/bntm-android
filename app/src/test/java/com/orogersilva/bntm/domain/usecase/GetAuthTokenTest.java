package com.orogersilva.bntm.domain.usecase;

import com.orogersilva.bntm.AuthDataSource;
import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;
import com.orogersilva.bntm.domain.repository.AuthRepository;
import com.orogersilva.bntm.domain.usecase.GetAuthTokenUseCase;
import com.orogersilva.bntm.domain.usecase.impl.GetAuthTokenUseCaseImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by orogersilva on 12/17/2016.
 */

public class GetAuthTokenTest {

    // region FIELDS

    private String mName;
    private String mEmail;

    @Mock
    private Executor mExecutor;
    @Mock
    private MainThread mMainThread;
    @Mock
    private AuthRepository mAuthRepository;
    @Mock
    private GetAuthTokenUseCase.Callback mGetAuthTokenUseCaseCallback;
    @Captor
    private ArgumentCaptor<AuthDataSource.GetAuthTokenCallback> mGetAuthTokenDataSourceCallback;

    // endregion

    // region SETUP METHODS

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    // endregion

    // region TEST METHODS

    @Test
    public void getAuthToken_hasFailed() {

        // ARRANGE

        mName = null;
        mEmail = null;

        GetAuthTokenUseCaseImpl usecase = new GetAuthTokenUseCaseImpl(mName, mEmail, mExecutor,
                mMainThread, mGetAuthTokenUseCaseCallback, mAuthRepository);

        // ACT

        usecase.run();

        // ASSERT

        verify(mAuthRepository).getAuthToken(eq(mName), eq(mEmail), mGetAuthTokenDataSourceCallback.capture());

        mGetAuthTokenDataSourceCallback.getValue().onFailed();

        verifyNoMoreInteractions(mAuthRepository);
        verify(mGetAuthTokenUseCaseCallback).onFailed();
    }

    @Test
    public void getAuthToken_wasSuccessful() {

        // ARRANGE

        mName = "Roger Silva";
        mEmail = "orogersilva@gmail.com";

        final String EXPECTED_AUTH_TOKEN = "kd9DKzKZJ90sjzAL2mSJXsia";

        GetAuthTokenUseCaseImpl usecase = new GetAuthTokenUseCaseImpl(mName, mEmail, mExecutor,
                mMainThread, mGetAuthTokenUseCaseCallback, mAuthRepository);

        // ACT

        usecase.run();

        // ASSERT

        verify(mAuthRepository).getAuthToken(eq(mName), eq(mEmail), mGetAuthTokenDataSourceCallback.capture());

        mGetAuthTokenDataSourceCallback.getValue().onAuthTokenLoaded(EXPECTED_AUTH_TOKEN);

        verifyZeroInteractions(mAuthRepository);
        verify(mGetAuthTokenUseCaseCallback).onAuthTokenLoaded(EXPECTED_AUTH_TOKEN);
    }

    // endregion

    // region TEARDOWN METHODS

    @After
    public void tearDown() {

        mName = null;
        mEmail = null;
    }

    // endregion
}
