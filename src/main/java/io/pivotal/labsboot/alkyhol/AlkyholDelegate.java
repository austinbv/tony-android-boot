package io.pivotal.labsboot.alkyhol;

import android.os.Handler;

import java.util.List;
import java.util.concurrent.ExecutorService;

import io.pivotal.labsboot.framework.Delegate;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.AlkyholResponse;
import io.pivotal.labsboot.domain.Link;
import retrofit.RetrofitError;

class AlkyholDelegate extends Delegate {
    public static final String DEFAULT_REQUEST = "alkyhols";

    private volatile boolean mIsTaskCurrentlyRunning;

    private AlkyholResponse mLatestResponse;
    private final ExecutorService mExecutorService;
    private final AlkyholApiClient mAlkyholApiClient;
    private final AlkyholDataSource mAlkyholDataSource;

    public AlkyholDelegate(final ExecutorService executorService, final AlkyholApiClient alkyholApiClient, final AlkyholDataSource alkyholDataSource, final Handler handler) {
        super(handler);
        mExecutorService = executorService;
        mAlkyholApiClient = alkyholApiClient;
        mAlkyholDataSource = alkyholDataSource;
    }

    public void getAlkyhols() {
        getAlkyhols(DEFAULT_REQUEST);
    }

    private void getAlkyhols(final String href) {
        mIsTaskCurrentlyRunning = true;
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    mLatestResponse = mAlkyholApiClient.getAlkyhols(href);
                    final List<Alkyhol> alkyhols = mLatestResponse.getAlkyhols();
                    notifySuccess();
                    mAlkyholDataSource.addAlkyhols(alkyhols);
                } catch (final RetrofitError error) {
                    notifyError();
                }
                mIsTaskCurrentlyRunning = false;
            }
        });
    }

    public void loadNextPage() {
        if (!mIsTaskCurrentlyRunning) {
            final Link next = mLatestResponse.findLink("next");
            getAlkyhols(next.getHref());
        }
    }
}
