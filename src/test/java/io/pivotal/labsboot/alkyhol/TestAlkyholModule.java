package io.pivotal.labsboot.alkyhol;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@Module(
        injects = {
                AlkyholActivity.class,
                AlkyholActivityTest.class,
                AlkyholFragment.class,
                AlkyholFragmentTest.class
        },
        library = true,
        complete = false,
        overrides = true
)
public class TestAlkyholModule {
    @Provides
    @Singleton
    AlkyholDelegate providesDelegate() {
        return mock(AlkyholDelegate.class);
    }

    @Provides
    @Singleton
    AlkyholFragment.Factory providesFragment() {
        return mock(AlkyholFragment.Factory.class);
    }

    @Provides
    @Singleton
    AlkyholAdapter providesAdapter() {
        final AlkyholAdapter mockAdapter = mock(AlkyholAdapter.class);
        doReturn(1).when(mockAdapter).getViewTypeCount();
        return mockAdapter;
    }
}
