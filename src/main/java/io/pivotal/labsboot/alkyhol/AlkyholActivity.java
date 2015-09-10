package io.pivotal.labsboot.alkyhol;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.Bind;
import io.pivotal.labsboot.R;
import io.pivotal.labsboot.framework.InjectionActivity;

public class AlkyholActivity extends InjectionActivity {
    @Inject
    protected AlkyholFragment.Factory mFragmentFactory;

    @Bind(R.id.appbar_toolbar_tab_layout) TabLayout mTabLayout;
    @Bind(R.id.appbar_toolbar_toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_boot);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_alkyhol_content_view, mFragmentFactory.newInstance())
                .commit();
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mToolbar.setTitle("Alkyhol");
        setSupportActionBar(mToolbar);

        mTabLayout.addTab(mTabLayout.newTab().setText("Test One"), true);
        mTabLayout.addTab(mTabLayout.newTab().setText("Test Two"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Test Three"));
    }
}
