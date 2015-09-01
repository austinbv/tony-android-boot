package io.pivotal.labsboot;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.alkyhol.AlkyholModule;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

@Module(
    includes = {
        AlkyholModule.class
    },
    library = true,
    complete = false
)
public class ApplicationModule {
    private AlkyholApplication mAlkyholApplication;

    public ApplicationModule(final AlkyholApplication application) {
        mAlkyholApplication = application;
    }

    @Provides
    Context providesApplicationContext() {
        return mAlkyholApplication;
    }

    @Provides
    ObjectMapper providesObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    @Provides
    RestAdapter providesRestAdapter(final ObjectMapper objectMapper) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new JacksonConverter(objectMapper))
                .setEndpoint("ENDPOINT")
                .build();
    }

    @Provides
    @Named("MainThread")
    Handler providesMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    LayoutInflater providesLayoutInflater() {
        return LayoutInflater.from(mAlkyholApplication);
    }

    @Provides
    RequestManager providesGlide(final Context context) {
        return Glide.with(context);
    }
}
