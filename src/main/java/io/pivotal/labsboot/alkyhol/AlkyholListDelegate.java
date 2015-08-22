package io.pivotal.labsboot.alkyhol;

import android.os.Handler;
import android.os.Looper;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import io.pivotal.labsboot.ErrorListener;
import io.pivotal.labsboot.Injector;
import io.pivotal.labsboot.SuccessListener;
import retrofit.RetrofitError;

class AlkyholListDelegate {
    @Inject
    protected AlkyholApiClient mAlkyholApiClient;

    private ExecutorService mExecutorService;
    private Set<WeakReference<ErrorListener>> mErrorListeners = new HashSet<>();
    private Set<WeakReference<SuccessListener>> mSuccessListeners = new HashSet<>();

    public AlkyholListDelegate(final Injector injector, final ExecutorService executorService) {
        injector.inject(this);
        mExecutorService = executorService;
    }

    public void getAlkyhols() {
        mExecutorService.submit(new Runnable(){
            @Override
            public void run() {
            try {
                final AlkyholResponse response = mAlkyholApiClient.getAlkyhols();
                final List<Alkyhol> alkyhols = response.getAlkyhols();
                notifySuccess(alkyhols);
            } catch(final RetrofitError error) {
                notifyError();
            }
            }
        });
    }

    public void registerSuccess(final SuccessListener successListener) {
        mSuccessListeners.add(new WeakReference<>(successListener));
    }

    public void registerError(final ErrorListener errorListener) {
        mErrorListeners.add(new WeakReference<>(errorListener));
    }

    public void notifySuccess(final Object object) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                for (final WeakReference<SuccessListener> reference : mSuccessListeners) {
                    final SuccessListener successListener = reference.get();
                    if (successListener != null) {
                        successListener.onSuccess(object);
                    }
                }
            }
        });
    }

    public void notifyError() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                for (final WeakReference<ErrorListener> reference : mErrorListeners) {
                    final ErrorListener errorListener = reference.get();
                    if (errorListener != null) {
                        errorListener.onError();
                    }
                }
            }
        });
    }
}
