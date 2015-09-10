package io.pivotal.labsboot.framework;

import org.junit.Test;

import dagger.ObjectGraph;
import io.pivotal.labsboot.framework.ApplicationInjector;
import io.pivotal.labsboot.framework.InjectionFragment;

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