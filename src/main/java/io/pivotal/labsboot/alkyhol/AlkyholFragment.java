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
import io.pivotal.labsboot.R;
import io.pivotal.labsboot.framework.ErrorListener;
import io.pivotal.labsboot.framework.SuccessListener;
import io.pivotal.labsboot.injection.InjectionFragment;

public class AlkyholFragment extends InjectionFragment implements ErrorListener, SuccessListener {
    @Inject
    protected AlkyholDelegate mAlkyholDelegate;
    @Inject
    protected AlkyholAdapter mAlkyholAdapter;

    @Bind(R.id.fragment_alkyhollist_list_view)
    protected ListView mListView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alkyhollist_list_view, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setAdapter(mAlkyholAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        mAlkyholDelegate.registerSuccess(this);
        mAlkyholDelegate.registerError(this);
        mAlkyholDelegate.getAlkyhols();
    }

    @Override
    public void onStop() {
        super.onStop();

        mAlkyholDelegate.unregisterSuccess(this);
        mAlkyholDelegate.unregisterError(this);
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity(), "Request complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "There has been an error", Toast.LENGTH_LONG).show();
    }

    public static class Factory {
        public Fragment newInstance() {
            return new AlkyholFragment();
        }
    }
}
