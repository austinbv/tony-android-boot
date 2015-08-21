package io.pivotal.labsboot.alkyhol;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import javax.inject.Inject;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.TestInjector;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class AlkyholListFragmentTest {

    @Inject
    protected AlkyholListDelegate mockDelegate;
    @Inject
    protected AlkyholListAdapter mockAdapter;

    private AlkyholListFragment fragment;

    @Before
    public void setup() {
        TestInjector.inject(this);
        fragment = (AlkyholListFragment) new AlkyholListFragment.Factory().newInstance();
    }

    @Test
    public void newInstance() {
        assertThat(fragment).isInstanceOf(AlkyholListFragment.class);
    }

    @Test
    public void onCreation_addsAdapter() {
        FragmentTestUtil.startFragment(fragment);

        assertThat(fragment.mListView.getAdapter()).isEqualTo(mockAdapter);
    }

    @Test
    public void onCreation_registersWithDelegate_andMakesCall() {
        FragmentTestUtil.startFragment(fragment);

        final InOrder inOrder = inOrder(mockDelegate);
        inOrder.verify(mockDelegate).registerSuccess(mockAdapter);
        inOrder.verify(mockDelegate).registerError(fragment);
        inOrder.verify(mockDelegate).getAlkyhols();
    }
}