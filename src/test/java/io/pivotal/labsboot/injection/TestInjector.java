package io.pivotal.labsboot.injection;

import org.robolectric.RuntimeEnvironment;

public class TestInjector {
    public static void inject(final Object injectable) {
        ((Injector) RuntimeEnvironment.application).inject(injectable);
    }
}
