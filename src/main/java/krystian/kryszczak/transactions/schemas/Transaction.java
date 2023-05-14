package krystian.kryszczak.transactions.schemas;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Transaction(String debitAccount, String creditAccount, float amount) {}
