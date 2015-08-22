package io.pivotal.labsboot.alkyhol;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.Injector;
import retrofit.RestAdapter;

@Module(
        injects = {
            AlkyholActivity.class,
            AlkyholListFragment.class,
            AlkyholListDelegate.class
        },
        library = true,
        complete = false
)
public class AlkyholModule {
    @Provides
    AlkyholApiClient providesApiClient(final RestAdapter restAdapter) {
        return new AlkyholApiClient(restAdapter);
    }

    @Provides
    ExecutorService providesExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    AlkyholListDelegate providesDelegate(final Injector injector, final ExecutorService executor) {
        return new AlkyholListDelegate(injector, executor);
    }

    @Provides
    AlkyholListFragment.Factory providesFragment() {
        return new AlkyholListFragment.Factory();
    }

    @Provides
    AlkyholListAdapter providesAdapter(final Context context) {
        return new AlkyholListAdapter(context, android.R.layout.simple_list_item_1);
    }
}