package krystian.kryszczak.transactions.service;

import jakarta.inject.Singleton;
import krystian.kryszczak.transactions.schemas.Account;
import krystian.kryszczak.transactions.schemas.Transaction;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Singleton
@AllArgsConstructor
public final class TransactionsService {

    public Account[] report(final Transaction[] transactions) {
        final Map<String, Account> map = new HashMap<>();

        for (final var transaction : transactions) {
            final var debitAccount = getOrCreateIfNotExists(map, transaction.debitAccount());
            final var creditAccount = getOrCreateIfNotExists(map, transaction.creditAccount());

            final float amount = transaction.amount();
            debitAccount.debit(creditAccount, amount);
        }

        return map.values().stream().sorted().toArray(Account[]::new);
    }

    private Account getOrCreateIfNotExists(@NotNull Map<String, Account> map, @NotNull String accountId) {
        Account account = map.get(accountId);
        if (account != null) return account;

        account = new Account(accountId, 0, 0, 0.0f);
        map.put(accountId, account);
        return account;
    }
}
