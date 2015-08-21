package io.pivotal.labsboot.alkyhol;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import io.pivotal.labsboot.ErrorListener;
import io.pivotal.labsboot.SuccessListener;

class AlkyholListDelegate {
    private Set<WeakReference<SuccessListener>> mSuccessListeners = new HashSet<>();
    private Set<WeakReference<ErrorListener>> mErrorListeners = new HashSet<>();

    public AlkyholListDelegate() {}

    public void getAlkyhols() {

    }

    public void registerSuccess(final SuccessListener successListener) {
        mSuccessListeners.add(new WeakReference<>(successListener));
    }

    public void registerError(final ErrorListener errorListener) {
        mErrorListeners.add(new WeakReference<>(errorListener));
    }

    public void notifySuccess(final Object object) {
        for (final WeakReference<SuccessListener> reference : mSuccessListeners) {
            final SuccessListener successListener = reference.get();
            if (successListener != null) {
                successListener.onSuccess(object);
            }
        }
    }

    public void notifyError() {
        for (final WeakReference<ErrorListener> reference : mErrorListeners) {
            final ErrorListener errorListener = reference.get();
            if (errorListener != null) {
                errorListener.onError();
            }
        }
    }
}
