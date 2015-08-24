package io.pivotal.labsboot.alkyhol;

import android.os.Handler;

import java.util.List;
import java.util.concurrent.ExecutorService;

import io.pivotal.labsboot.framework.Delegate;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.AlkyholResponse;
import io.pivotal.labsboot.domain.Link;
import retrofit.RetrofitError;

class AlkyholListDelegate extends Delegate<List<Alkyhol>> {
    public static final String DEFAULT_REQUEST = "alkyhols";
    public static final int NEXT_PAGE_THRESHOLD = 3;

    private AlkyholResponse mLatestResponse;
    private ExecutorService mExecutorService;
    private AlkyholApiClient mAlkyholApiClient;

    public AlkyholListDelegate(final ExecutorService executorService, final AlkyholApiClient alkyholApiClient, final Handler handler) {
        super(handler);
        mExecutorService = executorService;
        mAlkyholApiClient = alkyholApiClient;
    }

    public void getAlkyhols() {
        getAlkyhols(DEFAULT_REQUEST);
    }

    private void getAlkyhols(final String href) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    mLatestResponse = mAlkyholApiClient.getAlkyhols(href);
                    final List<Alkyhol> alkyhols = mLatestResponse.getAlkyhols();
                    notifySuccess(alkyhols);
                } catch (final RetrofitError error) {
                    notifyError();
                }
            }
        });
    }

    public void loadNextPage() {
        final Link next = mLatestResponse.findLink("next");
        getAlkyhols(next.getHref());
    }
}
