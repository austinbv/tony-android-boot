package io.pivotal.labsboot.injection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.TestAndroidBootApplication;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class InjectionFragmentTest {
    private InjectionFragment spiedFragment;
    private InjectionActivity mockActivity;
    private TestAndroidBootApplication mockApplication;

    @Before
    public void setup() {
        spiedFragment = spy(InjectionFragment.class);
        mockActivity = mock(InjectionActivity.class);
        mockApplication = mock(TestAndroidBootApplication.class);
        doReturn(mockApplication).when(mockActivity).getApplication();
    }

    @Test
    public void onAttach() {
        spiedFragment.onAttach(mockActivity);
        verify(mockApplication).inject(spiedFragment);
    }

    @Test
    public void inject() {
        final Object mockObject = mock(Object.class);

        spiedFragment.onAttach(mockActivity);
        spiedFragment.inject(mockObject);

        verify(mockApplication).inject(mockObject);
    }

}