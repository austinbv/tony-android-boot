package io.pivotal.labsboot;

import android.app.Activity;
import android.os.Bundle;

public abstract class InjectionActivity extends Activity implements Injector {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
    }

    @Override
    public void inject(final Object injectable) {
        ((Injector) getApplication()).inject(injectable);
    }
}
