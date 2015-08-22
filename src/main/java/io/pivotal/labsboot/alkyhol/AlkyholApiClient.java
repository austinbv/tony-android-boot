package io.pivotal.labsboot.alkyhol;

import retrofit.RestAdapter;
import retrofit.http.GET;

class AlkyholApiClient {
    private final AlkyholRetrofitService mAlkyholRetrofitService;

    public AlkyholApiClient(final RestAdapter restAdapter) {
        mAlkyholRetrofitService = restAdapter.create(AlkyholRetrofitService.class);
    }

    public AlkyholResponse getAlkyhols() {
        return mAlkyholRetrofitService.getAlkyhols();
    }

    interface AlkyholRetrofitService {
        @GET("/products")
        AlkyholResponse getAlkyhols();
    }
}
