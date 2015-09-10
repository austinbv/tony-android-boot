package io.pivotal.labsboot.framework;

import dagger.ObjectGraph;

public class ApplicationInjector {
    private static ObjectGraph sGraph;

    public static void setGraph(final ObjectGraph graph) {
        sGraph = graph;
    }

    public static void inject(final Object injectable) {
        sGraph.inject(injectable);
    }
}
