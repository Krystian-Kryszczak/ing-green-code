package krystian.kryszczak.atmservice.components.schemas;

import io.micronaut.core.annotation.Introspected;
import lombok.ToString;

/**
 * ATMs details
 */
@ToString
@Introspected
public final class Atm extends Base {
    public Atm(int region, int atmId) {
        super(region, atmId);
    }
}
