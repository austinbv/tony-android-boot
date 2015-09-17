package io.pivotal.labsboot.alkyhol;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.framework.ApplicationInjector;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class, emulateSdk = 21)
public class AlkyholActivityTest {
    private ActivityController<AlkyholActivity> controller;

    @Before
    public void setup() {
        ApplicationInjector.inject(this);

        controller = Robolectric.buildActivity(AlkyholActivity.class);
    }

    @Test
    public void onCreation_actionBarIsSetup() {
        final AlkyholActivity activity = controller.create().postCreate(null).get();

        assertThat(activity.mToolbar.getTitle()).isEqualTo("Alkyhol");
        assertThat(activity.mTabLayout.getTabCount()).isEqualTo(3);
        assertThat(activity.mTabLayout.getTabAt(0).getText()).isEqualTo("Beer");
        assertThat(activity.mTabLayout.getTabAt(1).getText()).isEqualTo("Wine");
        assertThat(activity.mTabLayout.getTabAt(2).getText()).isEqualTo("Spirits");
        assertThat(activity.mViewPager).hasCurrentItem(0);
        assertThat(activity.mViewPager.getAdapter()).hasCount(3);
    }
}
