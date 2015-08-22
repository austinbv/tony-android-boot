package io.pivotal.labsboot;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

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
    private AndroidBootApplication mAndroidBootApplication;

    public ApplicationModule(final AndroidBootApplication application) {
        mAndroidBootApplication = application;
    }

    @Provides
    Context providesApplicationContext() {
        return mAndroidBootApplication;
    }

    @Provides
    RestAdapter providesRestAdapter(final ObjectMapper objectMapper) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new JacksonConverter(objectMapper))
                .setEndpoint("http://lcboapi.com")
                .build();
    }

    @Provides
    ObjectMapper providesObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return objectMapper;
    }

    @Provides
    Injector providesInjector() {
        return mAndroidBootApplication;
    }
}
