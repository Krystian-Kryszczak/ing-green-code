package krystian.kryszczak.atmservice.components.schemas;

import lombok.AllArgsConstructor;

/**
 * Type of request
 */
@AllArgsConstructor
public enum RequestType implements Comparable<RequestType> {
    FAILURE_RESTART(4),
    PRIORITY(2),
    SIGNAL_LOW(1),
    STANDARD(0);

    private final int priority;
}
