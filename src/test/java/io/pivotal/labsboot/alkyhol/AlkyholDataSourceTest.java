package io.pivotal.labsboot.alkyhol;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.AlkyholResponse;
import io.pivotal.labsboot.domain.Link;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AlkyholDataSourceTest {
    @Mock Handler mockHandler;
    private AlkyholDataSource empty;
    private AlkyholDataSource one;
    private AlkyholDataSource many;

    @Before
    public void setup() {
        empty = new AlkyholDataSource(mockHandler);
        one = new AlkyholDataSource(mockHandler);
        one.addAlkyholResponse(new AlkyholResponse(singletonList(new Alkyhol(1))));
        many = new AlkyholDataSource(mockHandler);
        many.addAlkyholResponse(new AlkyholResponse(asList(new Alkyhol(2), new Alkyhol(3))));
    }

    @Test
    public void size() {
        assertThat(empty.size()).isEqualTo(0);
        assertThat(one.size()).isEqualTo(1);
        assertThat(many.size()).isGreaterThan(1);
    }

    @Test
    public void addAlkyhols_notifiesListeners() {
        final AlkyholDataSource spiedDataSource = spy(many);

        spiedDataSource.addAlkyholResponse(new AlkyholResponse());

        verify(spiedDataSource).notifyDataSetChanged();
    }

    @Test
    public void getNextPageLink() {
        final AlkyholDataSource dataSource = new AlkyholDataSource(mockHandler);

        dataSource.addAlkyholResponse(new AlkyholResponse(EMPTY_LIST, singletonList(new Link("next", "nextPageUrl"))));

        assertThat(dataSource.getNextPageLink()).isEqualTo("nextPageUrl");
    }

    @Test
    public void getAlkyhol() {
        assertThat(one.getAlkyhol(0)).isEqualTo(new Alkyhol(1));
        assertThat(many.getAlkyhol(0)).isEqualTo(new Alkyhol(2));
        assertThat(many.getAlkyhol(1)).isEqualTo(new Alkyhol(3));
    }

    @Test
    public void nearEndOfData() {
        final AlkyholDataSource alkyholDataSource = new AlkyholDataSource(mockHandler, 1);
        alkyholDataSource.addAlkyholResponse(new AlkyholResponse(asList(new Alkyhol(1), new Alkyhol(2), new Alkyhol(3))));

        assertThat(alkyholDataSource.nearEndOfData(0)).isFalse();
        assertThat(alkyholDataSource.nearEndOfData(1)).isFalse();
        assertThat(alkyholDataSource.nearEndOfData(2)).isTrue();
    }
}