import java.util.Arrays;
import java.util.List;

public class Procedure {

    private final String    SYMBOL_DELIMITER = "1";
    public        int       oldState;
    public        Character acceptedInput;
    public        int       newState;
    public        Character output;
    public        Direction direction;

    public Procedure(String args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        List<String> items = Arrays.asList(args.split(SYMBOL_DELIMITER));
        this.oldState      = items.get(0).length() - 1;
        this.acceptedInput = items.get(1).charAt(0);
        this.newState      = items.get(2).length() - 1;
        this.output        = items.get(3).charAt(0);
        this.direction     = items.get(4) == "0" ? Direction.Left : Direction.Right; //TODO
    }

    @Override
    public String toString() {
        return "Procedure{" + "oldState=" + oldState + ", acceptedInput=" + acceptedInput + ", newState=" + newState + ", output=" + output + ", direction=" + direction + '}';
    }

}
