package io.pivotal.labsboot.alkyhol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.pivotal.labsboot.R;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.framework.AdapterHelper;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AlkyholAdapterTest {
    @Mock private LayoutInflater mockLayoutInflater;
    @Mock private AdapterHelper mockAdapterHelper;
    @Mock private AlkyholDelegate mockAlkyholDelegate;
    @Mock private AlkyholDataSource mockAlkyholDataSource;
    @Mock private AlkyholPresenter mMockAlkyholPresenter;
    @Mock private AlkyholViewHolder.Factory mockViewHolderFactory;

    private AlkyholAdapter adapter;

    @Before
    public void setup() {
        doReturn(new Alkyhol(1)).when(mockAlkyholDataSource).getAlkyhol(0);
        doReturn(new Alkyhol(2)).when(mockAlkyholDataSource).getAlkyhol(1);

        adapter = new AlkyholAdapter(
                mockLayoutInflater,
                mockViewHolderFactory,
                mMockAlkyholPresenter,
                mockAlkyholDelegate,
                mockAlkyholDataSource,
                mockAdapterHelper
        );
    }

    @Test
    public void onCreation_setsSelfAsDataListener() {
        verify(mockAlkyholDataSource).registerDataSetChangeLisener(adapter);
    }

    @Test
    public void onDataSetChanged() {
        adapter.onDataSetChanged();
        verify(mockAdapterHelper).notifyDataSetChanged(adapter);
    }


    @Test
    public void onCreateViewHolder() {
        final View mockView = mock(View.class);
        final ViewGroup mockViewGroup = mock(ViewGroup.class);
        final AlkyholViewHolder mockViewHolder = mock(AlkyholViewHolder.class);
        doReturn(mockViewHolder).when(mockViewHolderFactory).newViewHolder(any(View.class));
        doReturn(mockView).when(mockLayoutInflater).inflate(anyInt(), any(ViewGroup.class), anyBoolean());
        assertThat(adapter.onCreateViewHolder(mockViewGroup, 0)).isEqualTo(mockViewHolder);
        verify(mockViewHolderFactory).newViewHolder(mockView);
        verify(mockLayoutInflater).inflate(R.layout.list_item_alkyhol, mockViewGroup, false);
    }

    @Test
    public void onBindViewHolder() {
        final AlkyholViewHolder mockViewHolder = mock(AlkyholViewHolder.class);

        adapter.bindViewHolder(mockViewHolder, 0);

        verify(mMockAlkyholPresenter).hydrateView(mockViewHolder, new Alkyhol(1));
    }

    @Test
    public void getViewThreeFromEnd_makesRequestForNextPage() {
        doReturn(true).when(mockAlkyholDataSource).nearEndOfData(anyInt());

        adapter.onBindViewHolder(null, 0);

        verify(mockAlkyholDataSource).nearEndOfData(0);
        verify(mockAlkyholDelegate).loadNextPage();
    }
}