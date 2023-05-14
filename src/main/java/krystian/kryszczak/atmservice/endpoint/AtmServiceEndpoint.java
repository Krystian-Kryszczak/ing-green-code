package krystian.kryszczak.atmservice.endpoint;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import krystian.kryszczak.atmservice.components.schemas.Atm;
import krystian.kryszczak.atmservice.components.schemas.Task;
import krystian.kryszczak.atmservice.service.AtmService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller(AtmServiceEndpoint.url)
public final class AtmServiceEndpoint {
    public static final String url = "/atms/calculateOrder/";
    private final AtmService atmService;

    @Post
    public List<Atm> calculate(@NonNull @Body final List<Task> serviceTasks) {
        return atmService.calculate(serviceTasks);
    }
}
