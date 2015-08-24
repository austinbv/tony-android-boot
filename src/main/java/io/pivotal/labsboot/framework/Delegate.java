package io.pivotal.labsboot.framework;

import android.os.Handler;

import java.util.HashSet;
import java.util.Set;

public abstract class Delegate<TYPE> {
    private Handler mHandler;
    private Set<ErrorListener> mErrorListeners = new HashSet<>();
    private Set<SuccessListener<TYPE>> mSuccessListeners = new HashSet<>();

    public Delegate (final Handler handler) {
        mHandler = handler;
    }

    public void registerSuccess(final SuccessListener<TYPE> successListener) {
        mSuccessListeners.add(successListener);
    }

    public void registerError(final ErrorListener errorListener) {
        mErrorListeners.add(errorListener);
    }

    public void unregisterSuccess(final SuccessListener<TYPE> successListener) {
        mSuccessListeners.remove(successListener);
    }

    public void unregisterError(final ErrorListener errorListener) {
        mErrorListeners.remove(errorListener);
    }

    public void notifySuccess(final TYPE result) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                for (final SuccessListener<TYPE> listener : mSuccessListeners) {
                    listener.onSuccess(result);
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