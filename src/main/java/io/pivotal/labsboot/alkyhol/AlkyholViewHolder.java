package io.pivotal.labsboot.alkyhol;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.pivotal.labsboot.R;

class AlkyholViewHolder {
    @Bind(R.id.list_item_alkyhol_name) TextView name;
    @Bind(R.id.list_item_alkyhol_price) TextView price;
    @Bind(R.id.list_item_alkyhol_image) ImageView image;
    @Bind(R.id.list_item_alkyhol_container) TextView container;
    @Bind(R.id.list_item_alkyhol_content) TextView content;

    private AlkyholViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public static class Factory {
        public AlkyholViewHolder newViewHolder(final View view) {
            return new AlkyholViewHolder(view);
        }
    }
}
