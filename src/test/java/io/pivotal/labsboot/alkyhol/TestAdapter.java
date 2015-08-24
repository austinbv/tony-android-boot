package io.pivotal.labsboot.alkyhol;

import android.view.View;
import android.view.ViewGroup;

import org.robolectric.RuntimeEnvironment;

import java.util.List;

import io.pivotal.labsboot.framework.SuccessListener;
import io.pivotal.labsboot.domain.Alkyhol;

public class TestAdapter extends AlkyholListAdapter implements SuccessListener<List<Alkyhol>> {
    public TestAdapter() {
        super(null, null, null, null, null);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Alkyhol getItem(final int position) {
        return new Alkyhol();
    }

    @Override
    public long getItemId(final int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        return new View(RuntimeEnvironment.application);
    }

    @Override
    public void onSuccess(final List<Alkyhol> result) {
    }
}
