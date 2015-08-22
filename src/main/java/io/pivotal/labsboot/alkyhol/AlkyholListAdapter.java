package io.pivotal.labsboot.alkyhol;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import io.pivotal.labsboot.SuccessListener;

class AlkyholListAdapter extends ArrayAdapter<Alkyhol> implements SuccessListener<List<Alkyhol>> {
    public AlkyholListAdapter(final Context context, final int resource) {
        super(context, resource);
    }

    @Override
    public void onSuccess(final List<Alkyhol> result) {
        clear();
        addAll(result);
        notifyDataSetChanged();
    }
}
