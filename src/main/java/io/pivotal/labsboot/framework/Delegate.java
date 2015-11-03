package io.pivotal.labsboot.framework;

import java.util.HashSet;
import java.util.Set;

import retrofit.android.MainThreadExecutor;

public class Delegate {
    private final MainThreadExecutor mMainThreadExecutor;
    private final Set<ErrorListener> mErrorListeners;
    private final Set<SuccessListener> mSuccessListeners;

    public Delegate (final MainThreadExecutor mainThreadExecutor) {
        mMainThreadExecutor = mainThreadExecutor;
        mErrorListeners = new HashSet<>();
        mSuccessListeners = new HashSet<>();
    }

    public void registerSuccess(final SuccessListener successListener) {
        mSuccessListeners.add(successListener);
    }

    public void registerError(final ErrorListener errorListener) {
        mErrorListeners.add(errorListener);
    }

    public void unregisterSuccess(final SuccessListener successListener) {
        mSuccessListeners.remove(successListener);
    }

    public void unregisterError(final ErrorListener errorListener) {
        mErrorListeners.remove(errorListener);
    }

    public void notifySuccess() {
        mMainThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (final SuccessListener listener : mSuccessListeners) {
                    listener.onSuccess();
                }
            }
        });
    }

    public void notifyError() {
        mMainThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (final ErrorListener listener : mErrorListeners) {
                    listener.onError();
                }
            }
        });
    }
}