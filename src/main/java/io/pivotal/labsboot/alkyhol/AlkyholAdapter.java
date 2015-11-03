package io.pivotal.labsboot.alkyhol;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.pivotal.labsboot.R;
import io.pivotal.labsboot.framework.AdapterHelper;
import io.pivotal.labsboot.framework.DataSetChangeListener;

class AlkyholAdapter extends RecyclerView.Adapter<AlkyholViewHolder> implements DataSetChangeListener {
    private String mType;
    private AdapterHelper mAdapterHelper;
    private LayoutInflater mLayoutInflater;
    private AlkyholDelegate mAlkyholDelegate;
    private AlkyholPresenter mAlkyholPresenter;
    private AlkyholDataSource mAlkyholDataSource;
    private AlkyholViewHolder.Factory mViewHolderFactory;

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
        alkyholDataSource.registerDataSetChangeListener(this);
    }

    @Override
    public AlkyholViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mViewHolderFactory.newViewHolder(mLayoutInflater.inflate(R.layout.list_item_alkyhol, parent, false));
    }

    @Override
    public void onBindViewHolder(final AlkyholViewHolder holder, final int position) {
        if (mAlkyholDataSource.nearEndOfData(mType, position)) {
            mAlkyholDelegate.loadNextPage(mType);
        }
        mAlkyholPresenter.hydrateView(holder, mAlkyholDataSource.getAlkyhol(mType, position));
    }

    @Override
    public int getItemCount() {
        return mAlkyholDataSource.size(mType);
    }

    @Override
    public void onDataSetChanged() {
        mAdapterHelper.notifyDataSetChanged(this);
    }

    public void setType(String type) {
        mType = type;
    }
}