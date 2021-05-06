import java.util.Arrays;
import java.util.List;

public class UniversalTM {

    private final String        TM_DELIMITER        = "111";
    private final Configuration config;
    private final String        input;

    public UniversalTM(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        if (args.length > 1) {
            throw new IllegalArgumentException();
        }
        List<String> configAndInput = Arrays.asList(args[0].split(TM_DELIMITER));
        if (configAndInput.size() > 2) {
            throw new IllegalArgumentException();
        }

        config = new Configuration(configAndInput.get(0));
        input  = configAndInput.get(1);
    }

    public void parse(Mode mode) {

    }

    public enum Mode {Run, Step}

    public Configuration getConfig() {
        return config;
    }
}
