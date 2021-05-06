import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {

    private final String UEBERGANG_DELIMITER = "11";

    public Map<Integer, List<Procedure>> list = new HashMap<>();

    public Configuration(String args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        List<String> uebergaenge = Arrays.asList(args.split(UEBERGANG_DELIMITER));
        uebergaenge.forEach((uebergang) -> {
            Procedure procedure = new Procedure(uebergang);
            if (list.containsKey(procedure.oldState)) {
                List<Procedure> procedures = list.get(procedure.oldState);
                procedures.add(procedure);
                list.put(procedure.oldState, procedures);
            }
            else {
                List<Procedure> procedures = new ArrayList<>();
                procedures.add(procedure);
                list.put(procedure.oldState, procedures);
            }
        });
    }

}
