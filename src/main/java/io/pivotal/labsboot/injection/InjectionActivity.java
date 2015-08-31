package io.pivotal.labsboot.injection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class InjectionActivity extends AppCompatActivity implements Injector {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void inject(final Object injectable) {
        ApplicationInjector.inject(injectable);
    }
}
