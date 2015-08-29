package io.pivotal.labsboot.framework;

import android.os.Handler;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public abstract class DataSource {
    private Handler mHandler;
    private Set<WeakReference<DataSetChangeListener>> mChangeListeners = new HashSet<>();

    public DataSource(final Handler handler) {
        mHandler = handler;
    }

    public void registerDataSetChangeLisener(final DataSetChangeListener listener) {
        mChangeListeners.add(new WeakReference<>(listener));
    }

    public void notifyDataSetChanged() {
        mHandler.post(new Runnable() {
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
