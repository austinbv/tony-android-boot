package io.pivotal.labsboot.alkyhol;

import java.util.concurrent.ExecutorService;

import io.pivotal.labsboot.framework.Delegate;
import retrofit.RetrofitError;
import retrofit.android.MainThreadExecutor;

class AlkyholDelegate {
    public static final String DEFAULT_REQUEST = "alkyhols";

    private volatile boolean mIsTaskCurrentlyRunning;

    private final Delegate mDelegate;
    private final ExecutorService mExecutorService;
    private final AlkyholApiClient mAlkyholApiClient;
    private final AlkyholDataSource mAlkyholDataSource;

    public AlkyholDelegate(final ExecutorService executorService, final AlkyholApiClient alkyholApiClient, final AlkyholDataSource alkyholDataSource, final MainThreadExecutor mainThreadExecutor) {
        mDelegate = new Delegate(mainThreadExecutor);
        mExecutorService = executorService;
        mAlkyholApiClient = alkyholApiClient;
        mAlkyholDataSource = alkyholDataSource;
    }

    public void getAlkyhols(final String type) {
        getAlkyhols(type, DEFAULT_REQUEST);
    }

    private void getAlkyhols(final String type, final String href) {
        mIsTaskCurrentlyRunning = true;
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    mAlkyholDataSource.addAlkyholResponse(type, mAlkyholApiClient.getAlkyhols(type, href));
                    mDelegate.notifySuccess();
                } catch (final RetrofitError error) {
                    mDelegate.notifyError();
                }
                mIsTaskCurrentlyRunning = false;
            }
        });
    }

    public void loadNextPage(final String type) {
        if (!mIsTaskCurrentlyRunning) {
            getAlkyhols(type, mAlkyholDataSource.getNextPageLink(type));
        }
    }
}
