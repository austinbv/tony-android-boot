package io.pivotal.labsboot.alkyhol;

import android.os.Handler;
import android.view.LayoutInflater;

import com.bumptech.glide.RequestManager;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.framework.AdapterHelper;
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
    @Singleton
    AlkyholListDelegate providesDelegate(
            final RestAdapter restAdapter,
            @Named("MainThread") final Handler handler)
    {
        return new AlkyholListDelegate(Executors.newSingleThreadExecutor(), new AlkyholApiClient(restAdapter), handler);
    }

    @Provides
    AlkyholListFragment.Factory providesFragment() {
        return new AlkyholListFragment.Factory();
    }

    @Provides
    AlkyholListAdapter providesAdapter(
            final LayoutInflater layoutInflater,
            final RequestManager requestManager,
            final AlkyholListDelegate alkyholListDelegate
    ) {
        return new AlkyholListAdapter(layoutInflater, new AlkyholViewHolder.Factory(), new AlkyholListPresenter(requestManager), alkyholListDelegate, new AdapterHelper<Alkyhol>());
    }
}