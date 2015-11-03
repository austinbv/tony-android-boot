package io.pivotal.labsboot.alkyhol;

import android.os.Handler;
import android.view.LayoutInflater;

import com.bumptech.glide.RequestManager;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.framework.AdapterHelper;
import retrofit.RestAdapter;
import retrofit.android.MainThreadExecutor;

@Module(
        injects = {
            AlkyholActivity.class,
            AlkyholFragment.class
        },
        library = true,
        complete = false
)
public class AlkyholModule {
    @Provides
    @Singleton
    AlkyholDelegate providesDelegate(
            final RestAdapter restAdapter,
            final MainThreadExecutor mainThreadExecutor,
            final AlkyholDataSource alkyholDataSource)
    {
        return new AlkyholDelegate(Executors.newSingleThreadExecutor(), new AlkyholApiClient(restAdapter), alkyholDataSource, mainThreadExecutor);
    }

    @Provides
    @Singleton
    AlkyholDataSource providesDataSource(final MainThreadExecutor executor) {
        return new AlkyholDataSource(executor);
    }

    @Provides
    AlkyholFragment.Factory providesFragment() {
        return new AlkyholFragment.Factory();
    }

    @Provides
    AlkyholAdapter providesAdapter(
            final LayoutInflater layoutInflater,
            final RequestManager requestManager,
            final AlkyholDelegate alkyholDelegate,
            final AlkyholDataSource alkyholDataSource
    ) {
        return new AlkyholAdapter(layoutInflater, new AlkyholViewHolder.Factory(), new AlkyholPresenter(requestManager), alkyholDelegate, alkyholDataSource, new AdapterHelper());
    }
}