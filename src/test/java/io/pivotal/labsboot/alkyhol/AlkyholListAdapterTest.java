package io.pivotal.labsboot.alkyhol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import io.pivotal.labsboot.BuildConfig;

import static java.util.Arrays.asList;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class AlkyholListAdapterTest {

    @Test
    public void onSuccess_setsDataAndNotifies() {
        final AlkyholListAdapter adapter = new AlkyholListAdapter(RuntimeEnvironment.application, android.R.layout.simple_list_item_1);
        adapter.onSuccess(asList(new Alkyhol(), new Alkyhol(), new Alkyhol()));
        assertThat(adapter).hasCount(3);
        assertThat(Shadows.shadowOf(adapter).wasNotifyDataSetChangedCalled()).isTrue();

        adapter.onSuccess(asList(new Alkyhol(), new Alkyhol()));
        assertThat(adapter).hasCount(2);
        assertThat(Shadows.shadowOf(adapter).wasNotifyDataSetChangedCalled()).isTrue();
    }

}