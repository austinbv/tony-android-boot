package io.pivotal.labsboot.alkyhol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import io.pivotal.labsboot.R;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.framework.AdapterHelper;
import io.pivotal.labsboot.framework.SuccessListener;

class AlkyholListAdapter extends BaseAdapter implements SuccessListener<List<Alkyhol>> {
    private List<Alkyhol> mAlkyhols;
    private LayoutInflater mLayoutInflater;
    private AdapterHelper<Alkyhol> mAdapterHelper;
    private AlkyholListDelegate mAlkyholListDelegate;
    private AlkyholListPresenter mAlkyholListPresenter;
    private AlkyholViewHolder.Factory mViewHolderFactory;

    public AlkyholListAdapter(
            final LayoutInflater layoutInflater,
            final AlkyholViewHolder.Factory viewHolderFactory,
            final AlkyholListPresenter alkyholListPresenter,
            final AlkyholListDelegate alkyholListDelegate,
            final AdapterHelper<Alkyhol> adapterHelper
    ) {
        mLayoutInflater = layoutInflater;
        mViewHolderFactory = viewHolderFactory;
        mAlkyholListPresenter = alkyholListPresenter;
        mAlkyholListDelegate = alkyholListDelegate;
        mAdapterHelper = adapterHelper;
        mAlkyhols = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mAlkyhols.size();
    }

    @Override
    public Alkyhol getItem(final int position) {
        return mAlkyhols.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        if (position == mAlkyhols.size() - AlkyholListDelegate.NEXT_PAGE_THRESHOLD) {
            mAlkyholListDelegate.loadNextPage();
        }

        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_alkyhol, parent, false);
            view.setTag(mViewHolderFactory.newViewHolder(view));
        }

        return mAlkyholListPresenter.hydrateView(mAdapterHelper.getItem(this, position), view);
    }

    @Override
    public void onSuccess(final List<Alkyhol> result) {
        mAlkyhols.addAll(result);
        mAdapterHelper.notifyDataSetChanged(this);
    }

}
