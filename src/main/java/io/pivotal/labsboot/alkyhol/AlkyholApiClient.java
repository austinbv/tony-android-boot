package io.pivotal.labsboot.alkyhol;

import io.pivotal.labsboot.domain.AlkyholResponse;
import retrofit.RestAdapter;
import retrofit.http.EncodedPath;
import retrofit.http.GET;

class AlkyholApiClient {
    private final AlkyholRetrofitService mAlkyholRetrofitService;

    public AlkyholApiClient(final RestAdapter restAdapter) {
        mAlkyholRetrofitService = restAdapter.create(AlkyholRetrofitService.class);
    }

    public AlkyholResponse getAlkyhols(final String type, final String href) {
        return mAlkyholRetrofitService.getAlkyhols(href);
    }

    interface AlkyholRetrofitService {
        @GET("/{href}")
        AlkyholResponse getAlkyhols(@EncodedPath("href") final String href);
    }
}
