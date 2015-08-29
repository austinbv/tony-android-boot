package io.pivotal.labsboot.framework;

import android.widget.BaseAdapter;

public class AdapterHelper {
    public void notifyDataSetChanged(final BaseAdapter adapter){
        adapter.notifyDataSetChanged();
    }
}
