package io.pivotal.labsboot.framework;

import org.junit.Test;

import dagger.ObjectGraph;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InjectionActivityTest {

    @Test
    public void inject() {
        final InjectionActivity activity = new InjectionActivity() {};
        final ObjectGraph mockObjectGraph = mock(ObjectGraph.class);
        ApplicationInjector.setGraph(mockObjectGraph);
        final Object mockObject = mock(Object.class);

        activity.inject(mockObject);

        verify(mockObjectGraph).inject(mockObject);
    }
}