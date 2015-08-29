package io.pivotal.labsboot.alkyhol;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.framework.DataSource;

class AlkyholDataSource extends DataSource {
    private final int mDataEndThreshold;
    private final ArrayList<Alkyhol> mAlkyhols;

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

    public void addAlkyhols(final List<Alkyhol> alkyhols) {
        mAlkyhols.addAll(alkyhols);
        notifyDataSetChanged();
    }

    public Alkyhol getAlkyhol(final int index) {
        return mAlkyhols.get(index);
    }

    public boolean nearEndOfData(final int index) {
        return index == (size() - mDataEndThreshold);
    }
}
