package io.pivotal.labsboot;

import dagger.Module;
import io.pivotal.labsboot.alkyhol.TestAlkyholModule;

@Module(
        includes = {
                TestAlkyholModule.class
        },
        library = true,
        complete = false
)
public class TestApplicationModule {}
