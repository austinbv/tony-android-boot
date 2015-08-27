package io.pivotal.labsboot.alkyhol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.pivotal.labsboot.R;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.framework.AdapterHelper;

import static java.util.Arrays.asList;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AlkyholListAdapterTest {
    @Mock private LayoutInflater mockLayoutInflater;
    @Mock private AdapterHelper<Alkyhol> mockAdapterHelper;
    @Mock private AlkyholListDelegate mockAlkyholListDelegate;
    @Mock private AlkyholListPresenter mockAlkyholListPresenter;
    @Mock private AlkyholViewHolder.Factory mockViewHolderFactory;

    private AlkyholListAdapter adapter;

    @Before
    public void setup() {
        adapter = new AlkyholListAdapter(
                mockLayoutInflater,
                mockViewHolderFactory,
                mockAlkyholListPresenter,
                mockAlkyholListDelegate,
                mockAdapterHelper
        );
    }

    @Test
    public void onSuccess_setsDataAndNotifies() {
        adapter.onSuccess(asList(new Alkyhol(), new Alkyhol(), new Alkyhol()));
        assertThat(adapter).hasCount(3);
        verify(mockAdapterHelper).notifyDataSetChanged(adapter);

        reset(mockAdapterHelper);

        adapter.onSuccess(asList(new Alkyhol(), new Alkyhol()));
        assertThat(adapter).hasCount(5);
        verify(mockAdapterHelper).notifyDataSetChanged(adapter);
    }

    @Test
    public void getItem() {
        final Alkyhol alkyhol = new Alkyhol();
        alkyhol.setId(1);
        final Alkyhol anotherAlkyhol = new Alkyhol();
        anotherAlkyhol.setId(2);
        adapter.onSuccess(asList(alkyhol, anotherAlkyhol));

        assertThat(adapter.getItem(0)).isEqualTo(alkyhol);
        assertThat(adapter.getItem(1)).isEqualTo(anotherAlkyhol);
    }

    @Test
    public void getItemId() {
        assertThat(adapter.getItemId(0)).isEqualTo(0);
        assertThat(adapter.getItemId(1)).isEqualTo(1);
    }

    @Test
    public void getView() {
        final Alkyhol alkyhol = new Alkyhol();
        adapter.onSuccess(asList(alkyhol));
        final View mockView = mock(View.class);
        final ViewGroup mockViewGroup = mock(ViewGroup.class);
        final AlkyholViewHolder mockViewHolder = mock(AlkyholViewHolder.class);
        doReturn(mockViewHolder).when(mockViewHolderFactory).newViewHolder(any(View.class));
        doReturn(mockView).when(mockLayoutInflater).inflate(anyInt(), any(ViewGroup.class), anyBoolean());
        doReturn(mockView).when(mockAlkyholListPresenter).hydrateView(any(Alkyhol.class), any(View.class));

        final View actual = adapter.getView(0, null, mockViewGroup);

        verify(mockLayoutInflater).inflate(R.layout.list_item_alkyhol, mockViewGroup, false);
        verify(mockViewHolderFactory).newViewHolder(mockView);
        verify(mockView).setTag(mockViewHolder);
        verify(mockAlkyholListPresenter).hydrateView(alkyhol, mockView);

        assertThat(actual).isEqualTo(mockView);
    }

    @Test
    public void getView_recycles() {
        final Alkyhol alkyhol = new Alkyhol();
        adapter.onSuccess(asList(alkyhol));
        final View mockView = mock(View.class);
        adapter.getView(0, mockView, null);

        verify(mockAlkyholListPresenter).hydrateView(alkyhol, mockView);
    }

    @Test
    public void getViewThreeFromEnd_makesRequestForNextPage() {
        final List<Alkyhol> alkyhols = asList(new Alkyhol(), new Alkyhol(), new Alkyhol());
        adapter.onSuccess(alkyhols);

        adapter.getView(0, mock(View.class), null);

        verify(mockAlkyholListDelegate).loadNextPage();
    }
}