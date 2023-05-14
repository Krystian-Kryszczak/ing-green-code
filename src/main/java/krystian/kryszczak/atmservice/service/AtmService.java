package krystian.kryszczak.atmservice.service;

import jakarta.inject.Singleton;
import krystian.kryszczak.atmservice.components.schemas.Atm;
import krystian.kryszczak.atmservice.components.schemas.Task;

import java.util.*;

@Singleton
public final class AtmService {

    public List<Atm> calculate(final List<Task> serviceTasks) {
        Collections.sort(serviceTasks);

        final Iterator<Task> iterator = serviceTasks.iterator();
        final List<Atm> result = new LinkedList<>();

        Task previously = null;
        while (iterator.hasNext()) {
            final Task task = iterator.next();

            if (task.equals(previously)) {
                continue;
            }

            result.add(task.toAtm());

            previously = task;
        }

        return result;
    }


//    public Atm[] calculate(final Task[] serviceTasks) {
//        Arrays.sort(serviceTasks);
//        filterReplications(serviceTasks);
//
//        final int serviceTasksLength = serviceTasks.length;
//        final Atm[] atms = new Atm[serviceTasksLength];
//
//        int replications = 0;
//        for (int i = 0; i < serviceTasksLength; i++) {
//            if (serviceTasks[i] != null) {
//                atms[i] = serviceTasks[i].toAtm();
//            } else {
//                replications++;
//            }
//        }
//
//        final Atm[] result = new Atm[serviceTasksLength - replications];
//
//        int i = 0;
//        for (Atm atm : atms) {
//            if (atm != null) {
//                result[i] = atm;
//                i++;
//            }
//        }
//
//        return result;
//    }

//    private void filterReplications(final Task[] serviceTasks) {
//        for (int i = 0; i < serviceTasks.length-1; i++) {
//            Task task = serviceTasks[i];
//            if (task != null && task.equals(serviceTasks[i+1], false)) {
//                serviceTasks[i] = null;
//            }
//        }
//    }
}
