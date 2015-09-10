package io.pivotal.labsboot.alkyhol;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.Bind;
import io.pivotal.labsboot.R;
import io.pivotal.labsboot.framework.ErrorListener;
import io.pivotal.labsboot.framework.SuccessListener;
import io.pivotal.labsboot.framework.InjectionFragment;

public class AlkyholFragment extends InjectionFragment implements ErrorListener, SuccessListener {
    @Inject
    protected AlkyholDelegate mAlkyholDelegate;
    @Inject
    protected AlkyholAdapter mAlkyholAdapter;

    @Bind(R.id.fragment_alkyhol_recycler_view) protected RecyclerView mRecyclerView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alkyhollist_list_view, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setAdapter(mAlkyholAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        Snackbar.make(getView(), "Request complete", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onError() {
        Snackbar.make(getView(), "There has been an error", Snackbar.LENGTH_LONG).show();
    }

    public static class Factory {
        public Fragment newInstance() {
            return new AlkyholFragment();
        }
    }
}
