package io.pivotal.labsboot.alkyhol;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import io.pivotal.labsboot.SuccessListener;

public class AlkyholListAdapter extends BaseAdapter implements SuccessListener<Alkyhol> {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(final int i) {
        return null;
    }

    @Override
    public long getItemId(final int i) {
        return 0;
    }

    @Override
    public View getView(final int i, final View view, final ViewGroup viewGroup) {
        return null;
    }

    public void setAlkyhols(final List<Alkyhol> alkyhols) {

    }

    @Override
    public void onSuccess(final Alkyhol result) {

    }
}
