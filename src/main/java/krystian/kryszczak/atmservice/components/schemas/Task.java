package krystian.kryszczak.atmservice.components.schemas;

import io.micronaut.core.annotation.Introspected;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Introspected
public final class Task extends Base implements Comparable<Task> {
    private final RequestType requestType;

    public Task(int region, RequestType requestType, int atmId) {
        super(region, atmId);
        this.requestType = requestType;
    }

    public Atm toAtm() {
        return new Atm(this.region, this.atmId);
    }

    @Override
    public int compareTo(@NotNull Task o) {

        int result = Integer.compare(this.region, o.region);
        if (result != 0)
            return result;

        result = this.requestType.compareTo(o.requestType);
        if (result != 0)
            return result;

        return -Integer.compare(this.atmId, o.atmId);
    }

    public boolean equals(Task task, boolean considerRequestType) {
        if (task == null) return false;
        if (considerRequestType) {
            return equals(task);
        }
        return this.region == task.region && this.atmId == task.atmId;
    }
}
