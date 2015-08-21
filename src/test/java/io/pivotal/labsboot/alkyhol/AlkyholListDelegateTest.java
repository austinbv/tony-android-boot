package io.pivotal.labsboot.alkyhol;

import org.junit.Test;

import io.pivotal.labsboot.ErrorListener;
import io.pivotal.labsboot.SuccessListener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlkyholListDelegateTest {

    @Test
    public void notifySuccess() {
        final AlkyholListDelegate delegate = new AlkyholListDelegate();
        final SuccessListener mockListener = mock(SuccessListener.class);
        final SuccessListener anotherMockListener = mock(SuccessListener.class);
        final Object object = new Object();

        delegate.registerSuccess(mockListener);
        delegate.registerSuccess(anotherMockListener);
        delegate.notifySuccess(object);

        verify(mockListener).onSuccess(object);
        verify(anotherMockListener).onSuccess(object);
    }

    @Test
    public void notifyFailure() {
        final AlkyholListDelegate delegate = new AlkyholListDelegate();
        final ErrorListener mockListener = mock(ErrorListener.class);
        final ErrorListener anotherMockListener = mock(ErrorListener.class);

        delegate.registerError(mockListener);
        delegate.registerError(anotherMockListener);
        delegate.notifyError();

        verify(mockListener).onError();
        verify(anotherMockListener).onError();
    }
}