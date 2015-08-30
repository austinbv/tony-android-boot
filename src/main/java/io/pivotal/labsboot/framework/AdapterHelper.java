package io.pivotal.labsboot.framework;

import android.support.v7.widget.RecyclerView;

public class AdapterHelper {
    public void notifyDataSetChanged(final RecyclerView.Adapter adapter){
        adapter.notifyDataSetChanged();
    }
}
