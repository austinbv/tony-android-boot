package io.pivotal.labsboot.alkyhol;

import org.junit.Test;

import io.pivotal.labsboot.domain.AlkyholResponse;
import retrofit.RestAdapter;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlkyholApiClientTest {

    @Test
    public void makesRequest() {
        final AlkyholResponse expected = new AlkyholResponse();
        final RestAdapter mockRestAdapter = mock(RestAdapter.class);
        final AlkyholApiClient.AlkyholRetrofitService mockService = mock(AlkyholApiClient.AlkyholRetrofitService.class);
        doReturn(expected).when(mockService).getAlkyhols(anyString());
        doReturn(mockService).when(mockRestAdapter).create(any(Class.class));
        final AlkyholApiClient apiClient = new AlkyholApiClient(mockRestAdapter);

        assertThat(apiClient.getAlkyhols("testType", "/alkyhols")).isEqualTo(expected);
        verify(mockRestAdapter).create(AlkyholApiClient.AlkyholRetrofitService.class);
        verify(mockService).getAlkyhols("/alkyhols");
    }
}