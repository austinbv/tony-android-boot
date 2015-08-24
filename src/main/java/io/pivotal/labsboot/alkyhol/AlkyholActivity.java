package io.pivotal.labsboot.alkyhol;

import android.os.Bundle;

import javax.inject.Inject;

import io.pivotal.labsboot.injection.InjectionActivity;
import io.pivotal.labsboot.R;

public class AlkyholActivity extends InjectionActivity {
    @Inject
    protected AlkyholListFragment.Factory mFragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_boot);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_alkyhol_content_view, mFragmentFactory.newInstance())
                .commit();
    }
}
