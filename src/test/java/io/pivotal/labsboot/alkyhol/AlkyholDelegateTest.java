package io.pivotal.labsboot.alkyhol;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.ExecutorService;

import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.AlkyholResponse;
import retrofit.RetrofitError;

import static io.pivotal.labsboot.alkyhol.AlkyholDelegate.DEFAULT_REQUEST;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AlkyholDelegateTest {
    @Mock ExecutorService mockExecutorService;
    @Mock AlkyholApiClient mockAlkyholApiClient;
    @Mock AlkyholDataSource mockAlkyholDataSource;
    @Mock Handler mockHandler;

    AlkyholDelegate delegate;

    @Before
    public void setup() {
        delegate = new AlkyholDelegate(mockExecutorService, mockAlkyholApiClient, mockAlkyholDataSource, mockHandler);
    }

    @Test
    public void getAlkyhols_onSuccess() {
        final AlkyholResponse response = new AlkyholResponse(singletonList(new Alkyhol()));
        doReturn(response).when(mockAlkyholApiClient).getAlkyhols(DEFAULT_REQUEST);
        final AlkyholDelegate spiedDelegate = spy(delegate);
        spiedDelegate.getAlkyhols();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutorService).submit(captor.capture());
        final Runnable runnable = captor.getValue();

        runnable.run();

        verify(mockAlkyholApiClient).getAlkyhols(DEFAULT_REQUEST);
        verify(spiedDelegate).notifySuccess();
        verify(mockAlkyholDataSource).addAlkyholResponse(response);
    }

    @Test
    public void getAlkyhols_onFailure() {
        doThrow(mock(RetrofitError.class)).when(mockAlkyholApiClient).getAlkyhols(DEFAULT_REQUEST);
        final AlkyholDelegate spiedDelegate = spy(delegate);
        spiedDelegate.getAlkyhols();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutorService).submit(captor.capture());
        final Runnable runnable = captor.getValue();

        runnable.run();

        verify(mockAlkyholApiClient).getAlkyhols(DEFAULT_REQUEST);
        verify(spiedDelegate).notifyError();
    }

    @Test
    public void loadNextPage_callsApiClientWithNextPage() {
        doReturn("/test.com/page=2").when(mockAlkyholDataSource).getNextPageLink();

        delegate.loadNextPage();
        ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutorService).submit(captor.capture());
        captor.getValue().run();

        verify(mockAlkyholApiClient).getAlkyhols("/test.com/page=2");
    }
}