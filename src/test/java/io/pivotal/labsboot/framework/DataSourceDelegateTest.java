package io.pivotal.labsboot.framework;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit.android.MainThreadExecutor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DataSourceDelegateTest {
    @Mock MainThreadExecutor mockExecutor;

    DataSourceDelegate dataSource;

    @Before
    public void setup() {
        dataSource = new DataSourceDelegate(mockExecutor);
    }

    @Test
    public void notifyDataSetChanged() {
        final DataSetChangeListener listener = mock(DataSetChangeListener.class);
        dataSource.registerDataSetChangeListener(listener);

        dataSource.notifyDataSetChanged();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutor).execute(captor.capture());
        captor.getValue().run();
        verify(listener).onDataSetChanged();
    }
}