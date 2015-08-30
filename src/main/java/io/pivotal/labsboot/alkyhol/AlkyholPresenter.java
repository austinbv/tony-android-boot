package io.pivotal.labsboot.alkyhol;

import com.bumptech.glide.RequestManager;

import io.pivotal.labsboot.R;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.Container;

class AlkyholPresenter {
    private final RequestManager mRequestManager;

    public AlkyholPresenter(final RequestManager requestManager) {
        mRequestManager = requestManager;
    }

    public void hydrateView(final AlkyholViewHolder holder, final Alkyhol alkyhol) {
        final Container container = alkyhol.getContainer();
        holder.name.setText(alkyhol.getName());
        holder.price.setText(alkyhol.getPrice());
        holder.content.setText(alkyhol.getAlcoholContent());
        holder.container.setText(
                String.format(container.getUnits() > 1 ? "%d %ss of %s" : "%d %s of %s",
                        container.getUnits(), container.getType(), container.getVolume())
        );
        if (alkyhol.getImage().getThumb() == null) {
            mRequestManager.load(R.drawable.placeholder).into(holder.image);
        } else {
            mRequestManager.load(alkyhol.getImage().getThumb()).into(holder.image);
        }
    }
}
