package io.pivotal.labsboot;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.alkyhol.AlkyholModule;
import retrofit.RestAdapter;

@Module(
    includes = {
        AlkyholModule.class
    },
    library = true,
    complete = false
)
public class ApplicationModule {
    private AndroidBootApplication mAndroidBootApplication;

    public ApplicationModule(final AndroidBootApplication application) {
        mAndroidBootApplication = application;
    }

    @Provides
    Context providesApplicationContext() {
        return mAndroidBootApplication;
    }

    @Provides
    RestAdapter providesRestAdapter() {
        return new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint("http://lcboapi.com").build();
    }

    @Provides
    Injector providesInjector() {
        return mAndroidBootApplication;
    }
}
