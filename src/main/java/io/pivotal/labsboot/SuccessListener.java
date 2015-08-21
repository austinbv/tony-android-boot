package io.pivotal.labsboot;

public interface SuccessListener<T> {
    void onSuccess(final T result);
}
