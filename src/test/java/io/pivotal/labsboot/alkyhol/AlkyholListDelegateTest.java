package io.pivotal.labsboot.alkyhol;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.ErrorListener;
import io.pivotal.labsboot.Injector;
import io.pivotal.labsboot.SuccessListener;
import io.pivotal.labsboot.TestInjector;
import retrofit.RetrofitError;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class AlkyholListDelegateTest {
    @Inject
    ExecutorService mockExecutorService;
    @Inject
    AlkyholApiClient mockAlkyholApiClient;

    AlkyholListDelegate delegate;

    @Before
    public void setup() {
        TestInjector.inject(this);
        delegate = new AlkyholListDelegate((Injector) RuntimeEnvironment.application, mockExecutorService);
    }

    @Test
    public void getAlkyhols_onSuccess() {
        final List<Alkyhol> alkyhols = singletonList(new Alkyhol());
        doReturn(new AlkyholResponse(alkyhols)).when(mockAlkyholApiClient).getAlkyhols();
        final AlkyholListDelegate spiedDelegate = spy(delegate);
        spiedDelegate.getAlkyhols();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutorService).submit(captor.capture());
        final Runnable runnable = captor.getValue();

        runnable.run();

        verify(mockAlkyholApiClient).getAlkyhols();
        verify(spiedDelegate).notifySuccess(alkyhols);
    }

    @Test
    public void getAlkyhols_onFailure() {
        doThrow(mock(RetrofitError.class)).when(mockAlkyholApiClient).getAlkyhols();
        final AlkyholListDelegate spiedDelegate = spy(delegate);
        spiedDelegate.getAlkyhols();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutorService).submit(captor.capture());
        final Runnable runnable = captor.getValue();

        runnable.run();

        verify(mockAlkyholApiClient).getAlkyhols();
        verify(spiedDelegate).notifyError();
    }

    @Test
    public void notifySuccess() {
        final SuccessListener mockListener = mock(SuccessListener.class);
        final SuccessListener anotherMockListener = mock(SuccessListener.class);
        final Object object = new Object();

        delegate.registerSuccess(mockListener);
        delegate.registerSuccess(anotherMockListener);
        delegate.notifySuccess(object);

        verify(mockListener).onSuccess(object);
        verify(anotherMockListener).onSuccess(object);
    }

    @Test
    public void notifyFailure() {
        final ErrorListener mockListener = mock(ErrorListener.class);
        final ErrorListener anotherMockListener = mock(ErrorListener.class);

        delegate.registerError(mockListener);
        delegate.registerError(anotherMockListener);
        delegate.notifyError();

        verify(mockListener).onError();
        verify(anotherMockListener).onError();
    }
}