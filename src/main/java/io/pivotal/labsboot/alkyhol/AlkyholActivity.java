package io.pivotal.labsboot.alkyhol;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.Bind;
import io.pivotal.labsboot.R;
import io.pivotal.labsboot.framework.InjectionActivity;

public class AlkyholActivity extends InjectionActivity {
    @Bind(R.id.appbar_toolbar_tab_layout) protected TabLayout mTabLayout;
    @Bind(R.id.appbar_toolbar_toolbar) protected Toolbar mToolbar;
    @Bind(R.id.activity_alkyhol_content_pager) protected ViewPager mViewPager;

    @Inject protected AlkyholFragment.Factory mFragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_boot);
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mToolbar.setTitle("Alkyhol");
        setSupportActionBar(mToolbar);

        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(final int position) {
                final String type;
                switch(position) {
                    case 0:
                        type = "beer";
                        break;
                    case 1:
                        type = "wine";
                        break;
                    case 2:
                        type = "spirits";
                        break;
                    default:
                        type = "";
                }
                return mFragmentFactory.newInstance(type);
            }

            @Override
            public CharSequence getPageTitle(final int position) {
                switch(position) {
                    case 0:
                        return "Beer";
                    case 1:
                        return "Wine";
                    case 2:
                        return "Spirits";
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
