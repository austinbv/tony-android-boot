package io.pivotal.labsboot.alkyhol;

import android.os.Handler;

import java.util.ArrayList;

import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.AlkyholResponse;
import io.pivotal.labsboot.domain.Link;
import io.pivotal.labsboot.framework.DataSource;

class AlkyholDataSource extends DataSource {
    private final int mDataEndThreshold;
    private final ArrayList<Alkyhol> mAlkyhols;
    private Link mNextPageLink;

    public AlkyholDataSource(final Handler handler) {
        this(handler, 5);
    }

    public AlkyholDataSource(final Handler handler, final int threshold) {
        super(handler);
        mAlkyhols = new ArrayList<>();
        mDataEndThreshold = threshold;
    }

    public int size() {
        return mAlkyhols.size();
    }

    public Alkyhol getAlkyhol(final int index) {
        return mAlkyhols.get(index);
    }

    public boolean nearEndOfData(final int index) {
        return size() > 0 && index == (size() - mDataEndThreshold);
    }

    public void addAlkyholResponse(final AlkyholResponse alkyholResponse) {
        mAlkyhols.addAll(alkyholResponse.getAlkyhols());
        mNextPageLink = alkyholResponse.findLink("next");
        notifyDataSetChanged();
    }

    public String getNextPageLink() {
        return mNextPageLink.getHref();
    }
}
