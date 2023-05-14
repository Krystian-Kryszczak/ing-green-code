package krystian.kryszczak.transactions.endpoint;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import krystian.kryszczak.transactions.schemas.Account;
import krystian.kryszczak.transactions.schemas.Transaction;
import krystian.kryszczak.transactions.service.TransactionsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller(TransactionsEndpoint.url)
public final class TransactionsEndpoint {
    public static final String url = "/transactions/report";
    private final TransactionsService transactionsService;

    @Post
    public Account[] report(@Body final Transaction[] transactions) {
        return transactionsService.report(transactions);
    }
}
