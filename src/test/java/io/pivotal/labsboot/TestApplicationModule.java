package io.pivotal.labsboot;

import android.content.Context;

import org.robolectric.RuntimeEnvironment;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.alkyhol.TestAlkyholModule;

@Module(
        includes = {
                TestAlkyholModule.class
        },
        library = true,
        complete = false
)
public class TestApplicationModule {
    @Provides
    Context providesApplicationContext() {
        return RuntimeEnvironment.application;
    }

    @Provides
    Injector providesInjector() {
        return (Injector) RuntimeEnvironment.application;
    }
}
