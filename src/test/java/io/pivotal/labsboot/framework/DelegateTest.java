package io.pivotal.labsboot.framework;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.List;

import io.pivotal.labsboot.domain.Alkyhol;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DelegateTest {
    Handler mockHandler;

    TestDelegate delegate;

    @Before
    public void setup() {
        mockHandler = mock(Handler.class);
        delegate = new TestDelegate(mockHandler);
    }

    @Test
    public void registerAndUnregisterSuccess() {
        final TestSuccessListener successListener = new TestSuccessListener();
        final TestSuccessListener anotherSuccessListener = new TestSuccessListener();
        delegate.registerSuccess(successListener);
        delegate.registerSuccess(anotherSuccessListener);
        delegate.unregisterSuccess(successListener);

        final List<Alkyhol> emptyList = Collections.EMPTY_LIST;
        notifySuccessWith(emptyList);
        assertThat(anotherSuccessListener.onSuccessWasCalledWith(emptyList)).isTrue();
        assertThat(successListener.successWasNotCalled()).isTrue();
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
        final List emptyList = Collections.EMPTY_LIST;

        delegate.registerSuccess(successListener);
        delegate.registerSuccess(anotherSuccessListener);

        notifySuccessWith(emptyList);

        assertThat(successListener.onSuccessWasCalledWith(emptyList)).isTrue();
        assertThat(anotherSuccessListener.onSuccessWasCalledWith(emptyList)).isTrue();
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

    private void notifySuccessWith(final List<Alkyhol> emptyList) {
        delegate.notifySuccess(emptyList);

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
        private List<Alkyhol> result;

        @Override
        public void onSuccess(final List<Alkyhol> result) {
            this.result = result;
        }

        public boolean onSuccessWasCalledWith(final List<Alkyhol> expected) {
            return expected.equals(result);
        }

        public boolean successWasNotCalled() {
            return result == null;
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