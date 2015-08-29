package io.pivotal.labsboot.framework;

import android.os.Handler;

import java.util.HashSet;
import java.util.Set;

public abstract class Delegate {
    private Handler mHandler;
    private Set<ErrorListener> mErrorListeners = new HashSet<>();
    private Set<SuccessListener> mSuccessListeners = new HashSet<>();

    public Delegate (final Handler handler) {
        mHandler = handler;
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
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                for (final SuccessListener listener : mSuccessListeners) {
                    listener.onSuccess();
                }
            }
        });
    }

    public void notifyError() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                for (final ErrorListener listener : mErrorListeners) {
                    listener.onError();
                }
            }
        });
    }
}