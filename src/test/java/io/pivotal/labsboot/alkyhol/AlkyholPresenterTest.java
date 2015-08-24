package io.pivotal.labsboot.alkyhol;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.RequestManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.pivotal.labsboot.domain.Alkyhol;
import io.pivotal.labsboot.domain.Container;
import io.pivotal.labsboot.domain.Image;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AlkyholPresenterTest {
    @Mock private RequestManager mockRequestManager;
    @Mock private DrawableTypeRequest mockDrawableTypeRequest;
    @Mock private LayoutInflater mockLayoutInflater;
    @Mock private TextView mockNameView;
    @Mock private TextView mockPriceView;
    @Mock private TextView mockContentView;
    @Mock private TextView mockContainerView;
    @Mock private ImageView mockImageView;
    @Mock private View mockView;

    private AlkyholListPresenter alkyholListPresenter;

    @Before
    public void before() {
        final AlkyholViewHolder mockAlkyholViewHolder = mock(AlkyholViewHolder.class);
        doReturn(mockDrawableTypeRequest).when(mockRequestManager).load(anyString());
        doReturn(mockAlkyholViewHolder).when(mockView).getTag();

        mockAlkyholViewHolder.name = mockNameView;
        mockAlkyholViewHolder.price = mockPriceView;
        mockAlkyholViewHolder.content = mockContentView;
        mockAlkyholViewHolder.container = mockContainerView;
        mockAlkyholViewHolder.image = mockImageView;

        alkyholListPresenter = new AlkyholListPresenter(mockRequestManager);
    }

    @Test
    public void getView() {
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

        alkyholListPresenter.hydrateView(alkyhol, mockView);

        verify(mockNameView).setText("Coors Light");
        verify(mockPriceView).setText("$4.25");
        verify(mockContentView).setText("4.5%");
        verify(mockContainerView).setText("2 cans of 355ml");
        verify(mockRequestManager).load("thumbImageUrl");
        verify(mockDrawableTypeRequest).into(mockImageView);
    }
}