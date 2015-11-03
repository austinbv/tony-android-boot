package io.pivotal.labsboot.alkyhol;

import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.AlkyholResponse;
import io.pivotal.labsboot.domain.Link;
import io.pivotal.labsboot.framework.DataSourceDelegate;
import lombok.experimental.Delegate;
import retrofit.android.MainThreadExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AlkyholDataSource {
    @Delegate private final DataSourceDelegate mDataSourceDelegate;
    private final int mDataEndThreshold;
    private final Map<String, List<Alkyhol>> mAlkyholsMap;
    private final Map<String, Link> mNextPageLinks;

    public AlkyholDataSource(final MainThreadExecutor executor) {
        this(executor, 5);
    }

    public AlkyholDataSource(final MainThreadExecutor executor, final int threshold) {
        mDataSourceDelegate = new DataSourceDelegate(executor);
        mAlkyholsMap = new HashMap<>();
        mNextPageLinks = new HashMap<>();
        mDataEndThreshold = threshold;
    }

    public int size(final String type) {
        if (!mAlkyholsMap.containsKey(type)) {
            return 0;
        }
        return mAlkyholsMap.get(type).size();
    }

    public Alkyhol getAlkyhol(final String type, final int index) {
        if (!mAlkyholsMap.containsKey(type)) {
            return null;
        }
        return mAlkyholsMap.get(type).get(index);
    }

    public boolean nearEndOfData(final String type, final int index) {
        return size(type) > 0 && index == (size(type) - mDataEndThreshold);
    }

    public void addAlkyholResponse(final String type, final AlkyholResponse alkyholResponse) {
        if (!mAlkyholsMap.containsKey(type)) {
            mAlkyholsMap.put(type, new ArrayList<Alkyhol>());
        }
        mAlkyholsMap.get(type).addAll(alkyholResponse.getAlkyhols());
        mNextPageLinks.put(type, alkyholResponse.findLink("next"));
        mDataSourceDelegate.notifyDataSetChanged();
    }

    public String getNextPageLink(final String type) {
        return mNextPageLinks.get(type).getHref();
    }
}