package io.pivotal.labsboot.alkyhol;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@Module(
        injects = {
                AlkyholActivity.class,
                AlkyholActivityTest.class,
                AlkyholListFragment.class,
                AlkyholListFragmentTest.class
        },
        library = true,
        complete = false,
        overrides = true
)
public class TestAlkyholModule {
    @Provides
    @Singleton
    AlkyholListDelegate providesDelegate() {
        return mock(AlkyholListDelegate.class);
    }

    @Provides
    @Singleton
    AlkyholListFragment.Factory providesFragment() {
        return mock(AlkyholListFragment.Factory.class);
    }

    @Provides
    @Singleton
    AlkyholListAdapter providesAdapter() {
        return spy(AlkyholListAdapter.class);
    }
}
