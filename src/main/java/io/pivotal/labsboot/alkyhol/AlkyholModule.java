package io.pivotal.labsboot.alkyhol;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
            AlkyholActivity.class,
            AlkyholListFragment.class
        },
        library = true,
        complete = false
)
public class AlkyholModule {
    @Provides
    AlkyholListDelegate providesDelegate() {
        return new AlkyholListDelegate();
    }

    @Provides
    AlkyholListFragment.Factory providesFragment() {
        return new AlkyholListFragment.Factory();
    }

    @Provides
    AlkyholListAdapter providesAdapter() {
        return new AlkyholListAdapter();
    }
}