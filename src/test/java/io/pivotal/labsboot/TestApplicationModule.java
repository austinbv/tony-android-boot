package io.pivotal.labsboot;

import android.content.Context;

import org.robolectric.RuntimeEnvironment;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.alkyhol.TestAlkyholModule;
import io.pivotal.labsboot.injection.Injector;

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
