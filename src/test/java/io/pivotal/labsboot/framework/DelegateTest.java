package io.pivotal.labsboot.framework;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.pivotal.labsboot.domain.Alkyhol;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DelegateTest {
    @Mock Handler mockHandler;

    TestDelegate delegate;

    @Before
    public void setup() {
        delegate = new TestDelegate(mockHandler);
    }

    @Test
    public void registerAndUnregisterSuccess() {
        final TestSuccessListener successListener = new TestSuccessListener();
        final TestSuccessListener anotherSuccessListener = new TestSuccessListener();
        delegate.registerSuccess(successListener);
        delegate.registerSuccess(anotherSuccessListener);
        delegate.unregisterSuccess(successListener);

        notifySuccessWith();
        assertThat(anotherSuccessListener.onSuccessWasCalled()).isTrue();
        assertThat(successListener.onSuccessWasCalled()).isFalse();
    }

    @Test
    public void registerAndUnregisterError() {
        final TestErrorListener errorListener = new TestErrorListener();
        final TestErrorListener anotherErrorListener = new TestErrorListener();
        delegate.registerError(errorListener);
        delegate.registerError(anotherErrorListener);
        delegate.unregisterError(errorListener);

        notifyError();
        assertThat(anotherErrorListener.onErrorWasCalled()).isTrue();
        assertThat(errorListener.onErrorWasCalled()).isFalse();
    }

    @Test
    public void notifySuccess() {
        final TestSuccessListener successListener = new TestSuccessListener();
        final TestSuccessListener anotherSuccessListener = new TestSuccessListener();

        delegate.registerSuccess(successListener);
        delegate.registerSuccess(anotherSuccessListener);

        notifySuccessWith();

        assertThat(successListener.onSuccessWasCalled()).isTrue();
        assertThat(anotherSuccessListener.onSuccessWasCalled()).isTrue();
    }

    @Test
    public void notifyFailure() {
        final TestErrorListener errorListener = new TestErrorListener();
        final TestErrorListener anotherErrorListener = new TestErrorListener();
        delegate.registerError(errorListener);
        delegate.registerError(anotherErrorListener);

        notifyError();

        assertThat(errorListener.onErrorWasCalled()).isTrue();
        assertThat(anotherErrorListener.onErrorWasCalled()).isTrue();
    }

    private void notifySuccessWith() {
        delegate.notifySuccess();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockHandler).post(captor.capture());

        captor.getValue().run();
    }

    private void notifyError() {
        delegate.notifyError();

        final ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockHandler).post(captor.capture());

        captor.getValue().run();
    }

    static class TestSuccessListener implements SuccessListener<List<Alkyhol>> {
        private boolean called = false;

        @Override
        public void onSuccess() {
            called = true;
        }

        public boolean onSuccessWasCalled() {
            return called;
        }
    }

    static class TestErrorListener implements ErrorListener {
        private boolean called;

        @Override
        public void onError() {
            this.called = true;
        }

        public boolean onErrorWasCalled() {
            return called;
        }
    }

    private static class TestDelegate extends Delegate {
        public TestDelegate(final Handler handler) {
            super(handler);
        }
    }
}