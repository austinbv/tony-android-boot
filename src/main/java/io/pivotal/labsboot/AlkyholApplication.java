package io.pivotal.labsboot;

import android.app.Application;

import dagger.ObjectGraph;
import io.pivotal.labsboot.framework.ApplicationInjector;

public class AlkyholApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setGraph(ObjectGraph.create(getModules()));
    }

    public Object[] getModules() {
        final Object[] modules = new Object[1];
        modules[0] = new ApplicationModule(this);
        return modules;
    }
}
