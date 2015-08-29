package io.pivotal.labsboot.framework;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DataSourceTest {
    @Mock Handler mockHandler;

    TestDataSource dataSource;

    @Before
    public void setup() {
        dataSource = new TestDataSource(mockHandler);
    }

    @Test
    public void notifyDataSetChanged() {
        final DataSetChangeListener listener = mock(DataSetChangeListener.class);
        dataSource.registerDataSetChangeLisener(listener);

        dataSource.notifyDataSetChanged();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockHandler).post(captor.capture());
        captor.getValue().run();
        verify(listener).onDataSetChanged();
    }

    private static class TestDataSource extends DataSource {
        public TestDataSource(final Handler handler) {
            super(handler);
        }
    }
}