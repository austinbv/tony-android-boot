package io.pivotal.labsboot.alkyhol;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.pivotal.labsboot.R;
import io.pivotal.labsboot.framework.AdapterHelper;
import io.pivotal.labsboot.framework.DataSetChangeListener;

class AlkyholAdapter extends RecyclerView.Adapter<AlkyholViewHolder> implements DataSetChangeListener {
    private LayoutInflater mLayoutInflater;
    private AdapterHelper mAdapterHelper;
    private AlkyholDelegate mAlkyholDelegate;
    private AlkyholDataSource mAlkyholDataSource;
    private AlkyholPresenter mAlkyholPresenter;
    private AlkyholViewHolder.Factory mViewHolderFactory;
    private String mType;

    public AlkyholAdapter(
            final LayoutInflater layoutInflater,
            final AlkyholViewHolder.Factory viewHolderFactory,
            final AlkyholPresenter alkyholPresenter,
            final AlkyholDelegate alkyholDelegate,
            final AlkyholDataSource alkyholDataSource,
            final AdapterHelper adapterHelper
    ) {
        mLayoutInflater = layoutInflater;
        mViewHolderFactory = viewHolderFactory;
        mAlkyholPresenter = alkyholPresenter;
        mAlkyholDelegate = alkyholDelegate;
        mAlkyholDataSource = alkyholDataSource;
        mAdapterHelper = adapterHelper;
        alkyholDataSource.registerDataSetChangeLisener(this);
    }

    @Override
    public AlkyholViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mViewHolderFactory.newViewHolder(mLayoutInflater.inflate(R.layout.list_item_alkyhol, parent, false));
    }

    @Override
    public void onBindViewHolder(final AlkyholViewHolder holder, final int position) {
        if (mAlkyholDataSource.nearEndOfData(position)) {
            mAlkyholDelegate.loadNextPage(mType);
        }
        mAlkyholPresenter.hydrateView(holder, mAlkyholDataSource.getAlkyhol(position));
    }

    @Override
    public int getItemCount() {
        return mAlkyholDataSource.size();
    }

    @Override
    public void onDataSetChanged() {
        mAdapterHelper.notifyDataSetChanged(this);
    }

    public void setType(String type) {
        mType = type;
    }
}