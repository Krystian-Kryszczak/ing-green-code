package krystian.kryszczak.transactions;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import krystian.kryszczak.transactions.endpoint.TransactionsEndpoint;
import krystian.kryszczak.transactions.schemas.Account;
import krystian.kryszczak.transactions.schemas.Transaction;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public final class TransactionsEndpointTest {
    private final HttpClient httpClient;
    private final TestUtils testUtils;

    public TransactionsEndpointTest(@Client(TransactionsEndpoint.url) HttpClient httpClient, TestUtils testUtils) {
        this.httpClient = httpClient;
        this.testUtils = testUtils;
    }

    private static final String resourcesFolderPath = "src/test/resources/transactions";
    private static final String requestJsonTemplate = resourcesFolderPath + "/example_request.json";
    private static final String responseJsonTemplate = resourcesFolderPath + "/example_response.json";

    @Test
    public void ingRequestResponseTest() throws IOException {
        final var requestBody = testUtils.parseFromJson(requestJsonTemplate, Transaction[].class);
        final var requiredResp = testUtils.parseFromJson(responseJsonTemplate, Account[].class);

        final var request = HttpRequest.POST("/", Transaction[].class).body(requestBody);
        final var retrieved = httpClient.toBlocking().retrieve(request, Account[].class);

        assertNotNull(retrieved);

        for (int i = 0; i < retrieved.length; i++) {
            assertEquals(retrieved[i], requiredResp[i]);
        }
    }
}
