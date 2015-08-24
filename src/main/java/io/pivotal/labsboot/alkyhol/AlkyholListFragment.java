package io.pivotal.labsboot.alkyhol;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import io.pivotal.labsboot.framework.ErrorListener;
import io.pivotal.labsboot.injection.InjectionFragment;
import io.pivotal.labsboot.R;

public class AlkyholListFragment extends InjectionFragment implements ErrorListener {
    @Inject
    protected AlkyholListDelegate mAlkyholListDelegate;
    @Inject
    protected AlkyholListAdapter mAlkyholListAdapter;

    @Bind(R.id.fragment_alkyhollist_list_view)
    protected ListView mListView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alkyhollist_list_view, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setAdapter(mAlkyholListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        mAlkyholListDelegate.registerSuccess(mAlkyholListAdapter);
        mAlkyholListDelegate.registerError(this);
        mAlkyholListDelegate.getAlkyhols();
    }

    @Override
    public void onStop() {
        super.onStop();

        mAlkyholListDelegate.unregisterSuccess(mAlkyholListAdapter);
        mAlkyholListDelegate.unregisterError(this);
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "There has been an error", Toast.LENGTH_LONG).show();
    }

    public static class Factory {
        public Fragment newInstance() {
            return new AlkyholListFragment();
        }
    }
}
