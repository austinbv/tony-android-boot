package io.pivotal.labsboot.framework;

import android.widget.BaseAdapter;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AdapterHelperTest {

    private BaseAdapter mockAdapter;
    private AdapterHelper adapterHelper;

    @Before
    public void setup() {
        adapterHelper = new AdapterHelper();
        mockAdapter = mock(BaseAdapter.class);
    }

    @Test
    public void notifyChange() {
        adapterHelper.notifyDataSetChanged(mockAdapter);

        verify(mockAdapter).notifyDataSetChanged();
    }

    @Test
    public void getItem() {
        final Object expected = new Object();
        doReturn(expected).when(mockAdapter).getItem(anyInt());

        assertThat(adapterHelper.getItem(mockAdapter, 0)).isEqualTo(expected);
        verify(mockAdapter).getItem(0);
    }
}