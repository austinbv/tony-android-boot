package io.pivotal.labsboot.alkyhol;

import android.view.View;

import com.bumptech.glide.RequestManager;

import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.Container;

class AlkyholListPresenter {
    private final RequestManager mRequestManager;

    public AlkyholListPresenter(final RequestManager requestManager) {
        mRequestManager = requestManager;
    }

    public View hydrateView(final Alkyhol alkyhol, final View view) {
        final AlkyholViewHolder holder = (AlkyholViewHolder) view.getTag();
        final Container container = alkyhol.getContainer();
        holder.name.setText(alkyhol.getName());
        holder.price.setText(alkyhol.getPrice());
        holder.content.setText(alkyhol.getAlcoholContent());
        holder.container.setText(
                String.format(container.getUnits() > 1 ? "%d %ss of %s" : "%d %s of %s",
                        container.getUnits(), container.getType(), container.getVolume())
        );
        mRequestManager.load(alkyhol.getImage().getThumb()).into(holder.image);
        return view;
    }
}
