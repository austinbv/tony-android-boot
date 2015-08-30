package io.pivotal.labsboot.alkyhol;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.RequestManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.pivotal.labsboot.R;
import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.Container;
import io.pivotal.labsboot.domain.Image;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AlkyholPresenterTest {
    @Mock private AlkyholViewHolder mockAlkyholViewHolder;
    @Mock private RequestManager mockRequestManager;
    @Mock private DrawableTypeRequest mockDrawableTypeRequest;
    @Mock private LayoutInflater mockLayoutInflater;
    @Mock private TextView mockNameView;
    @Mock private TextView mockPriceView;
    @Mock private TextView mockContentView;
    @Mock private TextView mockContainerView;
    @Mock private ImageView mockImageView;

    private AlkyholPresenter mAlkyholPresenter;

    @Before
    public void setup() {
        doReturn(mockDrawableTypeRequest).when(mockRequestManager).load(anyString());
        doReturn(mockDrawableTypeRequest).when(mockRequestManager).load(anyInt());

        mockAlkyholViewHolder.name = mockNameView;
        mockAlkyholViewHolder.price = mockPriceView;
        mockAlkyholViewHolder.content = mockContentView;
        mockAlkyholViewHolder.container = mockContainerView;
        mockAlkyholViewHolder.image = mockImageView;

        mAlkyholPresenter = new AlkyholPresenter(mockRequestManager);
    }

    @Test
    public void hydrateView() {
        final Container container = new Container();
        container.setType("can");
        container.setUnits(2);
        container.setVolume("355ml");
        final Image image = new Image();
        image.setThumb("thumbImageUrl");
        final Alkyhol alkyhol = new Alkyhol();
        alkyhol.setName("Coors Light");
        alkyhol.setPrice("$4.25");
        alkyhol.setAlcoholContent("4.5%");
        alkyhol.setContainer(container);
        alkyhol.setImage(image);

        mAlkyholPresenter.hydrateView(mockAlkyholViewHolder, alkyhol);

        verify(mockNameView).setText("Coors Light");
        verify(mockPriceView).setText("$4.25");
        verify(mockContentView).setText("4.5%");
        verify(mockContainerView).setText("2 cans of 355ml");
        verify(mockRequestManager).load("thumbImageUrl");
        verify(mockDrawableTypeRequest).into(mockImageView);
    }

    @Test
    public void hydrateViewWithNullImage() {
        final Container container = new Container();
        container.setType("can");
        container.setUnits(2);
        container.setVolume("355ml");
        final Image image = new Image();
        final Alkyhol alkyhol = new Alkyhol();
        alkyhol.setContainer(container);
        alkyhol.setImage(image);

        mAlkyholPresenter.hydrateView(mockAlkyholViewHolder, alkyhol);

        verify(mockRequestManager).load(R.drawable.placeholder);
    }
}