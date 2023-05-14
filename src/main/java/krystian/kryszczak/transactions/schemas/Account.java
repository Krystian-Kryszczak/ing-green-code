package krystian.kryszczak.transactions.schemas;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.micronaut.core.annotation.Introspected;
import krystian.kryszczak.transactions.serialize.FloatSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Introspected
public final class Account implements Comparable<Account> {
    private final String account;
    private int debitCount;
    private int creditCount;
    @JsonSerialize(using = FloatSerializer.class)
    private float balance;

    public void debit(final @NotNull Account creditAccount, final float amount) {
        incrementDebitCount();
        addToBalance(-amount);

        creditAccount.incrementCreditCount();
        creditAccount.addToBalance(amount);
    }

    public void incrementDebitCount() {
        debitCount++;
    }
    public void decrementDebitCount() {
        debitCount--;
    }
    public void incrementCreditCount() {
        creditCount++;
    }
    public void decrementCreditCount() {
        creditCount--;
    }

    public void addToBalance(final float amount) {
        balance += amount;
        balance = Math.round(balance * 100.0f) / 100.0f;
    }

    @Override
    public int compareTo(@NotNull Account account) {
        int result = Integer.compare(this.debitCount, account.debitCount);
        if (result != 0) return result;

        result = Integer.compare(this.creditCount, account.creditCount);
        if (result != 0) return result;

        result = Float.compare(this.balance, account.balance);
        if (result != 0) return result;

        return this.account.compareTo(account.account);
    }
}
