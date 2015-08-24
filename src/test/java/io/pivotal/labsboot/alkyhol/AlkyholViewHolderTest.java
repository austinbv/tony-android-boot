package io.pivotal.labsboot.alkyhol;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Test;

import io.pivotal.labsboot.R;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class AlkyholViewHolderTest {

    @Test
    public void creation() {
        final View mockView = mock(View.class);
        final TextView mockName = mock(TextView.class);
        final TextView mockPrice = mock(TextView.class);
        final ImageView mockImage = mock(ImageView.class);
        final TextView mockContent = mock(TextView.class);
        final TextView mockContainer = mock(TextView.class);
        doReturn(mockName).when(mockView).findViewById(R.id.list_item_alkyhol_name);
        doReturn(mockPrice).when(mockView).findViewById(R.id.list_item_alkyhol_price);
        doReturn(mockImage).when(mockView).findViewById(R.id.list_item_alkyhol_image);
        doReturn(mockContent).when(mockView).findViewById(R.id.list_item_alkyhol_content);
        doReturn(mockContainer).when(mockView).findViewById(R.id.list_item_alkyhol_container);
        final AlkyholViewHolder.Factory factory = new AlkyholViewHolder.Factory();

        final AlkyholViewHolder alkyholViewHolder = factory.newViewHolder(mockView);

        assertThat(alkyholViewHolder.name).isEqualTo(mockName);
        assertThat(alkyholViewHolder.price).isEqualTo(mockPrice);
        assertThat(alkyholViewHolder.image).isEqualTo(mockImage);
        assertThat(alkyholViewHolder.content).isEqualTo(mockContent);
        assertThat(alkyholViewHolder.container).isEqualTo(mockContainer);
    }

}