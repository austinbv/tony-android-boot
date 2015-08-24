package io.pivotal.labsboot.injection;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

public abstract class InjectionFragment extends Fragment implements Injector {

    private Injector mInjector;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mInjector = (Injector) activity.getApplication();
        inject(this);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void inject(final Object injectable) {
        mInjector.inject(injectable);
    }
}
