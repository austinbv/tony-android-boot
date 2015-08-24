package io.pivotal.labsboot.framework;

import android.widget.BaseAdapter;

public class AdapterHelper<T> {
    public void notifyDataSetChanged(final BaseAdapter adapter){
        adapter.notifyDataSetChanged();
    }

    public T getItem(final BaseAdapter adapter, final int position) {
        return (T) adapter.getItem(position);
    }
}
