package io.pivotal.labsboot.injection;

import org.junit.Test;

import dagger.ObjectGraph;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InjectionFragmentTest {

    @Test
    public void inject() {
        final InjectionFragment fragment = new InjectionFragment() {};
        final ObjectGraph mockObjectGraph = mock(ObjectGraph.class);
        final Object mockObject = mock(Object.class);
        ApplicationInjector.setGraph(mockObjectGraph);

        fragment.inject(mockObject);

        verify(mockObjectGraph).inject(mockObject);
    }
}