package io.pivotal.labsboot.alkyhol;

import android.support.design.widget.Snackbar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import javax.inject.Inject;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.injection.ApplicationInjector;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class, emulateSdk = 21)
public class AlkyholFragmentTest {
    @Inject
    protected AlkyholDelegate mockDelegate;
    @Inject
    protected AlkyholAdapter testAdapter;

    private AlkyholFragment fragment;

    @Before
    public void setup() {
        Snackbar.reset();
        ApplicationInjector.inject(this);
        fragment = (AlkyholFragment) new AlkyholFragment.Factory().newInstance();
    }

    @Test
    public void newInstance() {
        assertThat(fragment).isInstanceOf(AlkyholFragment.class);
    }

    @Test
    public void onCreation_addsAdapter() {
        FragmentTestUtil.startFragment(fragment);

        assertThat(fragment.mRecyclerView.getAdapter()).isEqualTo(testAdapter);
    }

    @Test
    public void onCreation_registersWithDelegate_andMakesCall() {
        FragmentTestUtil.startFragment(fragment);

        final InOrder inOrder = inOrder(mockDelegate);
        inOrder.verify(mockDelegate).registerSuccess(fragment);
        inOrder.verify(mockDelegate).registerError(fragment);
        inOrder.verify(mockDelegate).getAlkyhols();
    }

    @Test
    public void onStop_unregistersWithDelegate() {
        FragmentTestUtil.startFragment(fragment);
        fragment.onStop();

        verify(mockDelegate).unregisterSuccess(fragment);
        verify(mockDelegate).unregisterError(fragment);
    }

    @Test
    public void onSuccess_createsToast() {
        FragmentTestUtil.startFragment(fragment);

        fragment.onSuccess();

        assertThat(Snackbar.verifyMakeWith(fragment.getView(), "Request complete", Snackbar.LENGTH_LONG)).isTrue();
        assertThat(Snackbar.verifyShowCalled()).isTrue();
    }

    @Test
    public void onError_createsToast() {
        FragmentTestUtil.startFragment(fragment);

        fragment.onError();

        assertThat(Snackbar.verifyMakeWith(fragment.getView(), "There has been an error", Snackbar.LENGTH_LONG)).isTrue();
        assertThat(Snackbar.verifyShowCalled()).isTrue();
    }
}