package krystian.kryszczak.atmservice;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import krystian.kryszczak.atmservice.components.schemas.Atm;
import krystian.kryszczak.atmservice.components.schemas.Task;
import krystian.kryszczak.atmservice.endpoint.AtmServiceEndpoint;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public final class AtmServiceEndpointTest {
    private final HttpClient httpClient;
    private final TestUtils testUtils;

    public AtmServiceEndpointTest(@Client(AtmServiceEndpoint.url) HttpClient httpClient, TestUtils testUtils) {
        this.httpClient = httpClient;
        this.testUtils = testUtils;
    }

    private static final String resourcesFolderPath = "src/test/resources/atmservice";
    private static final String requestJsonTemplate = resourcesFolderPath + "/example_%s_request.json";
    private static final String responseJsonTemplate = resourcesFolderPath + "/example_%s_response.json";

    @Test
    public void ingRequestResponseTest() throws IOException {
        for (int j = 1; j <= 2; j++) {
            final var requestBody = testUtils.parseFromJson(String.format(requestJsonTemplate, j), Task[].class);
            final var requiredResp = testUtils.parseFromJson(String.format(responseJsonTemplate, j), Atm[].class);

            final var request = HttpRequest.POST("/", Task[].class).body(requestBody);
            final var retrieved = httpClient.toBlocking().retrieve(request, Atm[].class);

            assertNotNull(retrieved);

            for (int i = 0; i < retrieved.length; i++) {
                assertEquals(retrieved[i], requiredResp[i]);
            }
        }
    }
}
