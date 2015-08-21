package io.pivotal.labsboot.alkyhol;

import org.junit.Test;

import io.pivotal.labsboot.InjectionActivity;
import io.pivotal.labsboot.TestAndroidBootApplication;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class InjectionActivityTest {

    @Test
    public void inject() {
        final InjectionActivity injectionActivity = spy(InjectionActivity.class);
        final TestAndroidBootApplication mockApplication = mock(TestAndroidBootApplication.class);
        final Object mockObject = mock(Object.class);
        doReturn(mockApplication).when(injectionActivity).getApplication();

        injectionActivity.inject(mockObject);

        verify(mockApplication).inject(mockObject);
    }
}