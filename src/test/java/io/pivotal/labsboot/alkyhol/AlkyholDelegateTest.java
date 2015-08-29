package io.pivotal.labsboot.alkyhol;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.ExecutorService;

import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.AlkyholResponse;
import io.pivotal.labsboot.domain.Link;
import retrofit.RetrofitError;

import static io.pivotal.labsboot.alkyhol.AlkyholDelegate.DEFAULT_REQUEST;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
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
        final List<Alkyhol> alkyhols = singletonList(new Alkyhol());
        doReturn(new AlkyholResponse(alkyhols)).when(mockAlkyholApiClient).getAlkyhols(DEFAULT_REQUEST);
        final AlkyholDelegate spiedDelegate = spy(delegate);
        spiedDelegate.getAlkyhols();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutorService).submit(captor.capture());
        final Runnable runnable = captor.getValue();

        runnable.run();

        verify(mockAlkyholApiClient).getAlkyhols(DEFAULT_REQUEST);
        verify(spiedDelegate).notifySuccess();
        verify(mockAlkyholDataSource).addAlkyhols(alkyhols);
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
        final List<Alkyhol> alkyhols = singletonList(new Alkyhol());
        final List<Link> links = singletonList(new Link("next", "/test.com/page=2"));
        doReturn(new AlkyholResponse(alkyhols, links)).when(mockAlkyholApiClient).getAlkyhols(anyString());

        delegate.getAlkyhols();
        ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutorService).submit(captor.capture());
        Runnable runnable = captor.getValue();
        runnable.run();
        reset(mockExecutorService, mockAlkyholApiClient);
        doReturn(new AlkyholResponse(alkyhols, links)).when(mockAlkyholApiClient).getAlkyhols(anyString());

        delegate.loadNextPage();
        captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutorService).submit(captor.capture());
        runnable = captor.getValue();
        runnable.run();

        verify(mockAlkyholApiClient).getAlkyhols("/test.com/page=2");
    }
}