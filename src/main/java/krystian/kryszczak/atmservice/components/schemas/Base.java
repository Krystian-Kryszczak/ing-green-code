package krystian.kryszczak.atmservice.components.schemas;

import io.micronaut.core.annotation.Introspected;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Introspected
public abstract sealed class Base permits Task, Atm {
    protected final int region;
    protected final int atmId;
}
