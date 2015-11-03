package io.pivotal.labsboot.framework;

import retrofit.android.MainThreadExecutor;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class DataSourceDelegate {
    private MainThreadExecutor mExecutor;
    private Set<WeakReference<DataSetChangeListener>> mChangeListeners = new HashSet<>();

    public DataSourceDelegate(final MainThreadExecutor executor) {
        mExecutor = executor;
    }

    public void registerDataSetChangeListener(final DataSetChangeListener listener) {
        mChangeListeners.add(new WeakReference<>(listener));
    }

    public void notifyDataSetChanged() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (final WeakReference<DataSetChangeListener> reference : mChangeListeners) {
                    if (reference.get() == null) {
                        mChangeListeners.remove(reference);
                        continue;
                    }
                    reference.get().onDataSetChanged();
                }
            }
        });
    }
}
