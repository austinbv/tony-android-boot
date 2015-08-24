package io.pivotal.labsboot.framework;

public interface SuccessListener<T> {
    void onSuccess(final T result);
}
