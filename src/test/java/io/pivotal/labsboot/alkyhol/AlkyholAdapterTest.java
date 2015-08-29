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

import static org.fest.assertions.api.ANDROID.assertThat;
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
    public void getItem() {
        assertThat(adapter.getItem(0)).isEqualTo(new Alkyhol(1));
        assertThat(adapter.getItem(1)).isEqualTo(new Alkyhol(2));
    }

    @Test
    public void getItemId() {
        assertThat(adapter.getItemId(0)).isEqualTo(0);
        assertThat(adapter.getItemId(1)).isEqualTo(1);
    }

    @Test
    public void getView() {
        final View mockView = mock(View.class);
        final ViewGroup mockViewGroup = mock(ViewGroup.class);
        final AlkyholViewHolder mockViewHolder = mock(AlkyholViewHolder.class);
        doReturn(mockViewHolder).when(mockViewHolderFactory).newViewHolder(any(View.class));
        doReturn(mockView).when(mockLayoutInflater).inflate(anyInt(), any(ViewGroup.class), anyBoolean());
        doReturn(mockView).when(mMockAlkyholPresenter).hydrateView(any(Alkyhol.class), any(View.class));

        final View actual = adapter.getView(0, null, mockViewGroup);

        verify(mockLayoutInflater).inflate(R.layout.list_item_alkyhol, mockViewGroup, false);
        verify(mockViewHolderFactory).newViewHolder(mockView);
        verify(mockView).setTag(mockViewHolder);
        verify(mMockAlkyholPresenter).hydrateView(new Alkyhol(1), mockView);

        assertThat(actual).isEqualTo(mockView);
    }

    @Test
    public void getView_recycles() {
        final View mockView = mock(View.class);
        adapter.getView(0, mockView, null);

        verify(mMockAlkyholPresenter).hydrateView(new Alkyhol(1), mockView);
    }

    @Test
    public void getViewThreeFromEnd_makesRequestForNextPage() {
        doReturn(true).when(mockAlkyholDataSource).nearEndOfData(anyInt());

        adapter.getView(0, mock(View.class), null);

        verify(mockAlkyholDataSource).nearEndOfData(0);
        verify(mockAlkyholDelegate).loadNextPage();
    }
}