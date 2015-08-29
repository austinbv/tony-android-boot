package io.pivotal.labsboot.alkyhol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import io.pivotal.labsboot.R;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.framework.AdapterHelper;
import io.pivotal.labsboot.framework.DataSetChangeListener;

class AlkyholAdapter extends BaseAdapter implements DataSetChangeListener {
    private LayoutInflater mLayoutInflater;
    private AdapterHelper mAdapterHelper;
    private AlkyholDelegate mAlkyholDelegate;
    private AlkyholDataSource mAlkyholDataSource;
    private AlkyholPresenter mAlkyholPresenter;
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
        alkyholDataSource.registerDataSetChangeLisener(this);
    }

    @Override
    public int getCount() {
        return mAlkyholDataSource.size();
    }

    @Override
    public Alkyhol getItem(final int position) {
        return mAlkyholDataSource.getAlkyhol(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        if (mAlkyholDataSource.nearEndOfData(position)) {
            mAlkyholDelegate.loadNextPage();
        }

        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_alkyhol, parent, false);
            view.setTag(mViewHolderFactory.newViewHolder(view));
        }

        return mAlkyholPresenter.hydrateView(getItem(position), view);
    }

    @Override
    public void onDataSetChanged() {
        mAdapterHelper.notifyDataSetChanged(this);
    }
}
