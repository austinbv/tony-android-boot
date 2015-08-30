package io.pivotal.labsboot.alkyhol;

import android.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.R;
import io.pivotal.labsboot.injection.ApplicationInjector;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class, emulateSdk = 21)
public class AlkyholActivityTest {
    @Inject
    protected AlkyholFragment.Factory mockAlkyholFragmentFactory;

    @Before
    public void setup() {
        ApplicationInjector.inject(this);
    }

    @Test
    public void creatingActivity_addsFragmentToView() throws Exception {
        final Fragment fragment = new Fragment();
        doReturn(fragment).when(mockAlkyholFragmentFactory).newInstance();
        final AlkyholActivity activity = Robolectric.buildActivity(AlkyholActivity.class).create().get();

        verify(mockAlkyholFragmentFactory).newInstance();
        assertThat(activity.getFragmentManager().findFragmentById(R.id.activity_alkyhol_content_view)).isEqualTo(fragment);
    }
}
