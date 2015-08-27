package io.pivotal.labsboot.framework;

import android.widget.BaseAdapter;

import org.junit.Before;
import org.junit.Test;

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
}