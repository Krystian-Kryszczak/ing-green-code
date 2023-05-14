package krystian.kryszczak.onlinegame;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import krystian.kryszczak.onlinegame.endpoint.OnlineGameEndpoint;
import krystian.kryszczak.onlinegame.schemas.Clan;
import krystian.kryszczak.onlinegame.schemas.Players;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public final class OnlineGameEndpointTest {
    private final HttpClient httpClient;
    private final TestUtils testUtils;

    public OnlineGameEndpointTest(@Client(OnlineGameEndpoint.url) HttpClient httpClient, TestUtils testUtils) {
        this.httpClient = httpClient;
        this.testUtils = testUtils;
    }

    private static final String resourcesFolderPath = "src/test/resources/onlinegame";
    private static final String requestJsonTemplate = resourcesFolderPath + "/example_request.json";
    private static final String responseJsonTemplate = resourcesFolderPath + "/example_response.json";

    @Test
    public void ingRequestResponseTest() throws IOException {
        final var requestBody = testUtils.parseFromJson(requestJsonTemplate, Players.class);
        final var requiredResponse = testUtils.parseFromJson(responseJsonTemplate, Clan[][].class);

        final var request = HttpRequest.POST("/", Players.class).body(requestBody);
        final var retrieved = httpClient.toBlocking().retrieve(request, Clan[][].class);

        assertNotNull(retrieved);

        for (int i = 0; i < retrieved.length; i++) {
            final var retrievedPart = retrieved[i];
            final var requiredResponsePart = requiredResponse[i];

            assertEquals(requiredResponsePart.length, retrievedPart.length);

            for (int j = 0; j < requiredResponsePart.length; j++) {
                assertEquals(retrievedPart[j], requiredResponsePart[j]);
            }
        }
    }
}
